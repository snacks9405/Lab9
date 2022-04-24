package Lab9;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class TreeNurtureStation {
    private static ArrayList<Integer[]> bstData = new ArrayList<>();
    private static ArrayList<BestBinarySearchTree<Integer>> treeList = new ArrayList<>();

    public static void beginNurturing(String fileName) {
        parseFile(fileName);
        buildTrees();
        printTrees();
    }

    public static void parseFile(String fileName) {
        Scanner fileText = null;
        try {
            fileText = new Scanner(new BufferedReader(new FileReader(fileName)));
            while (fileText.hasNextLine()) {
                String[] lineDataString = fileText.nextLine().split(", ");
                Integer[] lineDataInteger = new Integer[lineDataString.length];
                for (int i = 0; i < lineDataString.length; i++) {
                    lineDataInteger[i] = Integer.parseInt(lineDataString[i]);
                }
                bstData.add(lineDataInteger);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void buildTrees() {
        for (Integer[] singleTreeData : bstData) {
            treeList.add(new BestBinarySearchTree<Integer>(singleTreeData));
            treeList.add(new BestBinarySearchTree<Integer>(singleTreeData, false));
        }
    }

    public static void printTrees() {
        for (int i = 0; i < treeList.size(); i++) {
            BestBinarySearchTree<Integer> bst = treeList.get(i);
            bst.printTree();
            System.out.printf(bst.isBinarySearchTree() ? "is a BST\n" : "is not a BST\n");
            Integer[] currentTreeDataLine = bstData.get(i / 2);
            Integer[] valuesToCheck = { currentTreeDataLine[0], currentTreeDataLine[currentTreeDataLine.length - 1],
                    currentTreeDataLine[0] * -1, currentTreeDataLine[currentTreeDataLine.length - 1] * -1 };
            for (int j = 0; j < valuesToCheck.length; j++) {
                if (bst.contains(valuesToCheck[j]) == null) {
                    System.out.printf("Does not contain %d; ", valuesToCheck[j]);
                } else {
                    System.out.printf("Does contain %d; ", valuesToCheck[j]);
                }
            }
            System.out.println();

        }
    }
}