package com.powerdata.openpa.tools;

public class FactorizedBMatrix
{
	float[] _bself, _bbrofs;
	int[] _pnode, _qnode;
	
	public FactorizedBMatrix(float[] bself, float[] bbrofs, int[] pnode,
			int[] qnode)
	{
		_bself = bself;
		_bbrofs = bbrofs;
		_pnode = pnode;
		_qnode = qnode;
	}
	
	public float[] solve(float[] mm, float[] vm)
	{
		int nnd = _bself.length;
		int nbr = _bbrofs.length;

		for (int i=0; i < nnd; ++i)
			mm[i] /= vm[i];
		
		/* run the forward reduction */
		for(int i=0; i < nbr; ++i)
		{
			mm[_qnode[i]] += _bbrofs[i] * mm[_pnode[i]];
		}
		
		/* backward substitution */
		float[] dx = new float[nnd];
//		for(int[] buslist : buses)
//		{
//			for (int b : buslist)
//			{
//				dx[b] = mm[b] / _bself[b];
//			}
//		}
		for(int i=nbr-1; i >= 0; --i)
		{
			int pn = _pnode[i];
			dx[pn] = mm[pn] / _bself[pn];
			dx[_pnode[i]] += _bbrofs[i] *  dx[_qnode[i]];
		}
		
		return dx;
	}
}
