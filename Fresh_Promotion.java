public class Main {
    public static void main(String[] args) {
             
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
The idea is to consider each item in the shopping cart as the start of the current code group we are processing.
If the current cart item did not match with the current code group, then move to the next cart item and try to match that with the current code group.
Return true if you can match all code groups before hitting end of the cart.

O(MN) solution where M is the average number of items in each code group, and N is the number of items in the shopping cart.
*/
    public static int winPrize(List<List<String>>codeList, List<String> shoppingCart) {
        // checking corner cases
        if(codeList == null || codeList.size() == 0)
            return 1;
        if(shoppingCart == null || shoppingCart.size() == 0)
            return 0;

        int indic = 0, next = 0;
        while(indic < codeList.size() && next + codeList.get(indic).size() <= shoppingCart.size()) {
            int equal = 0;
            for(int i = 0; i < codeList.get(indic).size(); i++) {
                if(!codeList.get(indic).get(i).equals("anything") &&
                    !shoppingCart.get(next+i).equals(codeList.get(indic).get(i))) {
                    equal = 1;
                    break;
                }
            }

            if(equal == 0) {
                next += codeList.get(indic).size();
                indic += 1;
            } else next += 1;
        }
        
        return indic == codeList.size() ? 1 : 0;
    }
}

