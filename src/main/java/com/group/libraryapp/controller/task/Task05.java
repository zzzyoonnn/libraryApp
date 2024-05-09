package com.group.libraryapp.controller.task;

import java.util.Scanner;

public class Task05 {
    private static Scanner scanner;
    private static int a;
    private static int[] arr = new int[6];

    public static void main(String[] args) throws Exception {
        // 숫자 입력받기
        inputNumber();

        // 주사위 결과 세기
        countsResult(a);

        // 각 숫자의 결과 출력하기
        printsResult();
    }

    // 숫자 입력받기
    public static int inputNumber() {
        System.out.print("숫자를 입력하세요 : ");

        scanner = new Scanner(System.in);
        a = scanner.nextInt();

        return a;
    }

    // 주사위 결과 세기
    public static void countsResult(int num) {
        for (int i = 0; i < a; i++) {
            double b = Math.random() * 6;

            if (b >= 0 && b < 1) arr[0]++;
            else if (b >= 1 && b < 2) arr[1]++;
            else if (b >= 2 && b < 3) arr[2]++;
            else if (b >= 3 && b < 4) arr[3]++;
            else if (b >= 4 && b < 5) arr[4]++;
            else if (b >= 5 && b < 6) arr[5]++;
        }
    }

    // 각 숫자의 결과 출력하기
    public static void printsResult() {
        for (int i = 0; i < 6; i++) {
            System.out.printf((i + 1) +"은 %d번 나왔습니다.\n", arr[i]);
        }
    }
}
