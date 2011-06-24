package ru.yandex.auto.clusterizer.util;

/**
 * Created by IntelliJ IDEA.
 *
 * @user: svyd
 * @date: 22.06.11
 */

public class DisjointSets {
    int[] p;
    int[] rank;

    public DisjointSets(int size) {
        p = new int[size];
        for (int i = 0; i < size; i++) {
            p[i] = i;
        }
        rank = new int[size];
    }

    public int get_root(int x) {
        while (x != p[x])
            x = p[x];
        return x;
    }

    public void unite(int a, int b) {
        a = get_root(a);
        b = get_root(b);
        if (a == b)
            return;
        if (rank[a] < rank[b]) {
            p[a] = b;
        } else {
            p[b] = a;
            if (rank[a] == rank[b])
                ++rank[a];
        }
    }
}