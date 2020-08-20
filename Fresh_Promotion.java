public class Main {
    public static void main(String[] args) {
        /*
        String[][] codeList1 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
        String[] shoppingCart1 = {"orange", "apple", "apple", "banana", "orange", "banana"};
        String[][] codeList2 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
        String[] shoppingCart2 = {"banana", "orange", "banana", "apple", "apple"};
        String[][] codeList3 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
        String[] shoppingCart3 = {"apple", "banana", "apple", "banana", "orange", "banana"};
        String[][] codeList4 = { { "apple", "apple" }, { "apple", "apple", "banana" } };
        String[] shoppingCart4 = {"apple", "apple", "apple", "banana"};
        String[][] codeList5 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
        String[] shoppingCart5 = {"orange", "apple", "apple", "banana", "orange", "banana"};
        String[][] codeList6 = { { "apple", "apple" }, { "banana", "anything", "banana" }  };
        String[] shoppingCart6 = {"apple", "apple", "orange", "orange", "banana", "apple", "banana", "banana"};
        String[][] codeList7= { { "anything", "apple" }, { "banana", "anything", "banana" }  };
        String[] shoppingCart7 = {"orange", "grapes", "apple", "orange", "orange", "banana", "apple", "banana", "banana"};
        String[][] codeList8= { { "anything"} };
        String[] shoppingCart8 = {"orange", "grapes", "orange", "apple", "orange", "banana", "apple", "banana", "banana"};
        System.out.println(solve(codeList1, shoppingCart1));
        System.out.println(solve(codeList2, shoppingCart2));
        System.out.println(solve(codeList3, shoppingCart3));
        System.out.println(solve(codeList4, shoppingCart4));
        System.out.println(solve(codeList5, shoppingCart5));
        System.out.println(solve(codeList6, shoppingCart6));
        System.out.println(solve(codeList7, shoppingCart7));
        System.out.println(winPrize(codeList8, shoppingCart8));
        */
        
        
        String[][] codeList1 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
        String[] shoppingCart1 = {"orange", "apple", "apple", "banana", "orange", "banana"};
        String[][] codeList2 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
        String[] shoppingCart2 = {"banana", "orange", "banana", "apple", "apple"};
        String[][] codeList3 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
        String[] shoppingCart3 = {"apple", "banana", "apple", "banana", "orange", "banana"};
        String[][] codeList4 = { { "apple", "apple" }, { "apple", "apple", "banana" } };
        String[] shoppingCart4 = {"apple", "apple", "apple", "banana"};
        String[][] codeList5 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
        String[] shoppingCart5 = {"orange", "apple", "apple", "banana", "orange", "banana"};
        String[][] codeList6 = { { "apple", "apple" }, { "banana", "anything", "banana" }  };
        String[] shoppingCart6 = {"apple", "apple", "orange", "orange", "banana", "apple", "banana", "banana"};
        String[][] codeList7= { { "anything", "apple" }, { "banana", "anything", "banana" }  };
        String[] shoppingCart7 = {"orange", "grapes", "apple", "orange", "orange", "banana", "apple", "banana", "banana"};
        String[][] codeList8 = { { "apple", "banana"}};
        String[] shoppingCart8 = {"apple", "apple", "banana"};
        String[][] codeList9 = {{"apple", "apple"}, {"banana", "oranage", "grape"}};
        String[] shoppingCart9 = {"apple", "apple", "banana", "oranage", "oranage", "banana", "oranage", "grape"};
        
        System.out.println(winPrize(codeList1, shoppingCart1));
        System.out.println(winPrize(codeList2, shoppingCart2));
        System.out.println(winPrize(codeList3, shoppingCart3));
        System.out.println(winPrize(codeList4, shoppingCart4));
        System.out.println(winPrize(codeList5, shoppingCart5));
        System.out.println(winPrize(codeList6, shoppingCart6));
        System.out.println(winPrize(codeList7, shoppingCart7));
        System.out.println(winPrize(codeList8, shoppingCart8));
        System.out.println(winPrize(codeList9, shoppingCart9));
}

/*
The idea is to consider each item in the shopping cart as the start of the current code group we are processing. If the current cart item did not match with the current code group, then move to the next cart item and try to match that with the current code group. Return true if you can match all code groups before hitting end of the cart.

O(MN) solution where M is the average number of items in each code group, and N is the number of items in the shopping cart.
*/
    public static int winPrize(String[][] codeList, String[] shoppingCart) {
        // checking corner cases
        if(codeList == null || codeList.length == 0)
            return 1;
        if(shoppingCart == null || shoppingCart.length == 0)
            return 0;

        int i = 0, j = 0;
        //int codeLen = codeList[i].length;
        while (i < codeList.length && j + codeList[i].length <= shoppingCart.length) {
            boolean match = true;
            for (int k = 0; k < codeList[i].length; k++) {
                if (!codeList[i][k].equals("anything") && !shoppingCart[j+k].equals(codeList[i][k])) {
                    match = false;
                    break;
                }
            }
            if (match) {
                j += codeList[i].length;
                i++;
            } else {
                j++;
            }
        }
        return (i == codeList.length) ? 1 : 0;
    }
}

