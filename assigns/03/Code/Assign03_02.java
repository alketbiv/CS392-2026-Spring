/*
HX-2026-02-05: 10 points
*/
public class Assign03_02 {
    static boolean balencedq(String text) {
	//
	// There are only '(', ')', '[', ']', '{', and '}'
	// appearing in [text]. This method should return
	// true if and only if the parentheses/brackets/braces
	// in [text] are balenced.
	// Your solution must make proper use of FnList (as a stack)!
	//

	FnList<Character> stack = new FnList<>();

	for (int i = 0; i < text.length(); i++) {
	    char c = text.charAt(i);

	    // Push opening brackets
	    if (c == '(' || c == '[' || c == '{') {
	        stack = new FnList<>(c, stack);
	    }
	    else {
	        if (stack.nilq()) return false;

	        char top = stack.hd();
	        stack = stack.tl();

	        if (c == ')' && top != '(') return false;
	        if (c == ']' && top != '[') return false;
	        if (c == '}' && top != '{') return false;
	    }
	}

	return stack.nilq();
    }

    public static void main(String[] argv) {
	System.out.println(balencedq("({()[({})]})")); // true
	System.out.println(balencedq("({()[({})])}")); // false
	System.out.println(balencedq("()[]{}"));       // true
	System.out.println(balencedq("([)]"));         // false
    }
}

