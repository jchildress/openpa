package com.powerdata.openpa.psse.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.powerdata.openpa.psse.PsseModel;
import com.powerdata.openpa.tools.BaseList;
import com.powerdata.openpa.tools.BaseObject;

public class ListDumper
{
	static final char Dlm = ',';
	public void dump(PsseModel model, File outdir) throws IOException,
			ReflectiveOperationException, RuntimeException
	{
		if (!outdir.exists()) outdir.mkdirs();
		Method[] methods = model.getClass().getMethods();
		for (Method m : methods)
		{
			Class<?> rtype = m.getReturnType();
			if (rtype.getPackage() != null && rtype.getPackage().getName()
					.equals("com.powerdata.openpa.psse"))
			{
				while (rtype != null && rtype != Object.class
						&& rtype != void.class)
				{
					if (rtype == BaseList.class
							&& m.getParameterTypes().length == 0)
					{
						String nm = m.getName();
						String title = nm.substring(3);
						File nfile = new File(outdir, title + ".csv");
						BaseList<?> list = (BaseList<?>) m.invoke(model,
								new Object[] {});
						dumpList(nfile, list);
					}
					rtype = rtype.getSuperclass();
				}
			}
		}
	}

	void dumpList(File nfile, BaseList<?> list) throws IOException,
			ReflectiveOperationException, IllegalArgumentException
	{
		Method[] methods = list.getClass().getMethods();
		ArrayList<Method> ometh = new ArrayList<>();
		ArrayList<String> mname = new ArrayList<>();
		for (Method m : methods)
		{
			Class<?> mclass = m.getReturnType();
			while (mclass != null && mclass != Object.class && mclass != void.class)
			{
				boolean isbasobj = false;
				for(Class<?> ic : mclass.getInterfaces()) {if (ic == BaseObject.class){isbasobj=true; break;}}
				if (mclass.isPrimitive() || isbasobj || mclass == String.class)
				{
					Class<?>[] ptype = m.getParameterTypes();
					if (ptype.length == 1 && ptype[0] == int.class)
					{

						String nm = m.getName();
						boolean yget = nm.startsWith("get");
						boolean yis = nm.startsWith("is");
						ometh.add(m);
						mname.add(nm.equals("get")?"toString()" : nm.substring(yget ? 3 : (yis ? 2 : 0)));
					}
				}
				mclass = mclass.getSuperclass();
			}
		}
		int n = list.size();
		if (!ometh.isEmpty() && n > 0)
		{
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					nfile)));
			pw.print(mname.get(0));
			for (int i = 1; i < mname.size(); ++i)
			{
				pw.print(Dlm);
				pw.print(mname.get(i));
			}
			pw.println();

			/* output data for each row */
			for (int i = 0; i < n; ++i)
			{
				for (int j = 0; j < ometh.size(); ++j)
				{
					/* output cell */
					if (j>0) pw.print(Dlm);
					Object v = ometh.get(j).invoke(list, i);
					boolean isstr = !Number.class.isInstance(v);
					if (isstr) pw.print('\'');
					String vs = v == null ? null : v.toString();
					pw.print((vs==null)?"<null>":vs);
					if (isstr) pw.print('\'');
				}
				pw.println();
			}
			pw.close();
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		String uri = null;
		File outdir = new File(System.getProperty("user.dir"));
		for(int i=0; i < args.length;)
		{
			String s = args[i++].toLowerCase();
			int ssx = 1;
			if (s.startsWith("--")) ++ssx;
			switch(s.substring(ssx))
			{
				case "uri":
					uri = args[i++];
					break;
				case "outdir":
					outdir = new File(args[i++]);
					break;
			}
		}
		if (uri == null)
		{
			System.err.format("Usage: -uri model_uri "
					+ "[ --outdir output_directory (deft to $CWD ]");
			System.exit(1);
		}
		
		new ListDumper().dump(PsseModel.Open(uri), outdir);
	}
}
