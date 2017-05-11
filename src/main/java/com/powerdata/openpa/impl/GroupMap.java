package com.powerdata.openpa.impl;
/*
 * Copyright (c) 2016, PowerData Corporation, Incremental Systems Corporation
 * All rights reserved.
 * Licensed under the BSD-3 Clause License.
 * See full license at https://powerdata.github.io/openpa/LICENSE.md
 */

import java.util.AbstractList;
import java.util.Arrays;

/**
 * Map groups back to original list offset
 */
public class GroupMap extends AbstractList<int[]> {
    protected int[] start, next, cnt;
    protected int ngrp, nxtsz = 0, lstcnt;

    protected GroupMap() {
    }

    /**
     * Set up a group map
     *
     * @param map  Array containing the group of each item at its offset
     * @param ngrp number of groups
     */
    public GroupMap(int[] map, int ngrp) {
        lstcnt = map.length;
        initmap(ngrp, map.length);
        setmap(map);
    }

    public GroupMap(int[] fmap, int[] tmap, int ngrp) {
        lstcnt = fmap.length;
        initmap(ngrp, fmap.length + tmap.length);
        setmap(fmap);
        setmap(tmap);
    }

    /**
     * initialize tha map
     *
     * @param ngrp   number of groups
     * @param maplen length of "next" array
     */
    void initmap(int ngrp, int maplen) {
        this.ngrp = ngrp;
        start = new int[ngrp];
        cnt = new int[ngrp];
        next = new int[maplen];
        Arrays.fill(start, -1);
        Arrays.fill(next, -1);
    }

    void setmap(int[] map) {
        for (int i = 0; i < map.length; ++i) {
            int g = map[i];
            if (g != -1) {
                next[nxtsz] = start[g];
                start[g] = nxtsz;
                ++cnt[g];
            }
            ++nxtsz;
        }
    }

    @Override
    public int[] get(int grpndx) {
        int[] rv = new int[cnt[grpndx]];
        fill(rv, 0, grpndx);
        return rv;
    }

    public int[] getComplement(int grpndx) {
        int[] rv = new int[lstcnt - cnt[grpndx]];
        int ofs = 0;
        for (int i = 0; i < ngrp; ++i) {
            if (grpndx != i) {
                fill(rv, ofs, i);
                ofs += cnt[i];
            }
        }
        return rv;
    }

    protected void fill(int[] rv, int ofs, int grpndx) {
        int s = start[grpndx];
        while (s != -1) {
            rv[ofs++] = s < lstcnt ? s : s - lstcnt;
            s = next[s];
        }
    }

    @Override
    public int size() {
        return ngrp;
    }

    /**
     * Find the number of items in a group
     *
     * @param grpndx Group index
     * @return the number of items in group
     */

    public int getCount(int grpndx) {
        return cnt[grpndx];
    }
}