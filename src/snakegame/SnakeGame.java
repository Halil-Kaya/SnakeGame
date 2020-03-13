/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

/**
 *
 * @author minter
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author halilubuntu
 */
public class SnakeGame {

    public static void main(String[] args) {

        int oyun[][] = new int[10][10];
        Random random = new Random();
        int x, y;

        ArrayList<int[]> yilan = new ArrayList<int[]>();

        int bir[] = new int[2];
        bir[0] = 3;
        bir[1] = 3;

        int iki[] = new int[2];
        iki[0] = 3;
        iki[1] = 4;

        int uc[] = new int[2];
        uc[0] = 3;
        uc[1] = 5;

        yilan.add(bir);
        yilan.add(iki);
        yilan.add(uc);

        doldur(oyun);

        for (int[] parca : yilan) {
            oyun[parca[0]][parca[1]] = 1;
        }
        x = random.nextInt(10);
        y = random.nextInt(10);
        while (oyun[y][x] == 1) {
            x = random.nextInt(10);
            y = random.nextInt(10);
        }
        oyun[y][x] = 2;
        int secim;
        while (true) {
            ekrana_yazdir(oyun);
            secim = yon();
            if (secim == 2) {
                asagi(yilan, oyun);
            } else if (secim == 4) {
                sola(yilan, oyun);
            } else if (secim == 5) {
                System.out.println("iyi gunler");
                break;
            } else if (secim == 6) {
                saga(yilan, oyun);
            } else if (secim == 8) {
                yukari(yilan, oyun);
            } else {
                System.out.println("tekrar");
            }
        }

    }

    public static int yon() {
        Scanner scan = new Scanner(System.in);
        System.out.println("...hangi yone dogru secin...");
        System.out.println("");
        System.out.println("\t\t(8)yukari");
        System.out.print("(4)sol");
        System.out.print("\t\t(5)cikis");
        System.out.println("\t\t(6)saga");
        System.out.println("\t\t(2)asagi");
        int yon = scan.nextInt();
        return yon;
    }

    public static void doldur(int[][] oyun) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (oyun[i][j] == 2) {
                    continue;
                }
                oyun[i][j] = 0;
            }
        }
    }

    public static void ekrana_yazdir(int[][] oyun_masasi) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                System.out.print("  " + oyun_masasi[y][x]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void oyunAlaniGuncelle(int[][] oyun, ArrayList<int[]> yilan) {
        for (int[] parca : yilan) {
            oyun[parca[0]][parca[1]] = 1;
        }
    }

    public static void asagi(ArrayList<int[]> yilan, int[][] oyun) {
        while (true) {
            int boyut = yilan.size();
            int[] deger = yilan.get(0);
            int[] eskiBas = yilan.get(0);
            int[] yeniBas = new int[2];
            if (oyun[((deger[0] + 1) + 10) % 10][deger[1]] == 1) {  //altinda bir varsa hareket etme
                break;
            }
            if (oyun[((deger[0] + 1) + 10) % 10][deger[1]] == 2) {
                yeniBas[1] = eskiBas[1];
                yeniBas[0] = ((eskiBas[0] + 1) + 10) % 10;
                yilan.add(0, yeniBas);
                doldur(oyun);
                oyunAlaniGuncelle(oyun, yilan);
                yemek(oyun);
                break;
            } else {

                yeniBas[1] = eskiBas[1];
                yeniBas[0] = ((eskiBas[0] + 1) + 10) % 10;
                yilan.remove(boyut - 1);
                yilan.add(0, yeniBas);
                doldur(oyun);
                oyunAlaniGuncelle(oyun, yilan);
                break;
            }
        }
    }

    public static void yukari(ArrayList<int[]> yilan, int[][] oyun) {
        while (true) {
            int boyut = yilan.size();
            int[] deger = yilan.get(0);
            int[] eskiBas = yilan.get(0);
            int[] yeniBas = new int[2];
            if (oyun[((deger[0] - 1) + 10) % 10][deger[1]] == 1) {  //yukarisinda bir varsa hareket etme
                break;
            }
            if (oyun[((deger[0] - 1) + 10) % 10][deger[1]] == 2) {
                yeniBas[1] = eskiBas[1];
                yeniBas[0] = ((eskiBas[0] - 1) + 10) % 10;
                yilan.add(0, yeniBas);
                doldur(oyun);
                oyunAlaniGuncelle(oyun, yilan);
                yemek(oyun);
                break;
            } else {

                yeniBas[1] = eskiBas[1];
                yeniBas[0] = ((eskiBas[0] - 1) + 10) % 10;
                yilan.remove(boyut - 1);
                yilan.add(0, yeniBas);
                doldur(oyun);
                oyunAlaniGuncelle(oyun, yilan);
                break;
            }
        }
    }

    public static void saga(ArrayList<int[]> yilan, int[][] oyun) {
        while (true) {
            int boyut = yilan.size();
            int[] deger = yilan.get(0);
            int[] eskiBas = yilan.get(0);
            int[] yeniBas = new int[2];
            if (oyun[deger[0]][((deger[1] + 1) + 10) % 10] == 1) {  //saginda bir varsa hareket etme
                break;
            }
            if (oyun[deger[0]][((deger[1] + 1) + 10) % 10] == 2) {
                yeniBas[0] = eskiBas[0];
                yeniBas[1] = ((eskiBas[1] + 1) + 10) % 10;
                yilan.add(0, yeniBas);
                doldur(oyun);
                oyunAlaniGuncelle(oyun, yilan);
                yemek(oyun);
                break;
            } else {

                yeniBas[0] = eskiBas[0];
                yeniBas[1] = ((eskiBas[1] + 1) + 10) % 10;
                yilan.remove(boyut - 1);
                yilan.add(0, yeniBas);
                doldur(oyun);
                oyunAlaniGuncelle(oyun, yilan);
                break;
            }
        }
    }

    public static void sola(ArrayList<int[]> yilan, int[][] oyun) {
        while (true) {
            int boyut = yilan.size();
            int[] deger = yilan.get(0);
            int[] eskiBas = yilan.get(0);
            int[] yeniBas = new int[2];
            if (oyun[deger[0]][((deger[1] - 1) + 10) % 10] == 1) {  //solunda bir varsa hareket etme
                break;
            }
            if (oyun[deger[0]][((deger[1] - 1) + 10) % 10] == 2) {
                yeniBas[0] = eskiBas[0];
                yeniBas[1] = ((eskiBas[1] - 1) + 10) % 10;
                yilan.add(0, yeniBas);
                doldur(oyun);
                oyunAlaniGuncelle(oyun, yilan);
                yemek(oyun);
                break;
            } else {

                yeniBas[0] = eskiBas[0];
                yeniBas[1] = ((eskiBas[1] - 1) + 10) % 10;
                yilan.remove(boyut - 1);
                yilan.add(0, yeniBas);
                doldur(oyun);
                oyunAlaniGuncelle(oyun, yilan);
                break;
            }
        }
    }

    public static void yemek(int[][] oyun) {

        Random random = new Random();
        int x, y;
        x = random.nextInt(10);
        y = random.nextInt(10);
        while (oyun[y][x] == 1) {
            x = random.nextInt(10);
            y = random.nextInt(10);
        }
        oyun[y][x] = 2;
    }

}