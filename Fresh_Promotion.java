private static int solve(String[][] codeList, String[] shoppingCart) {
	if(codeList == null || codeList.length == 0)
		return 1;
	if(shoppingCart == null || shoppingCart.length == 0)
		return 0;
	int i=0,j=0;
	for(int k=0;k<shoppingCart.length;k++) {
		if(codeList[i][j].equals(shoppingCart[k]) || codeList[i][j].equals("anything")) {
			j++;
			if(j == codeList[i].length) {
				i++;
				j=0;
			}
			if(i == codeList.length)
				return 1;
		}else {
			j = codeList[i][0].equals("anything") ? 1: 0;
		}
	}
	return 0;
}
