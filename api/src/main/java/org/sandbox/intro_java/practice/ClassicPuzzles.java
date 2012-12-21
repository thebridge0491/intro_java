package org.sandbox.intro_java.practice;

/** DocComment:
 * <p>Brief description.</p> */
public class ClassicPuzzles {
	private static class HanoiArgs {
		public int src, dest, spare, ndisks, acc, splits;
		
		public HanoiArgs(int src, int dest, int spare, int ndisks,
				int acc, int splits) {
			this.src = src; this.dest = dest; this.spare = spare;
			this.ndisks = ndisks; this.acc = acc; this.splits = splits;
		}
		
		public HanoiArgs() { this(0, 0, 0, 0, 0, 0);
		}
	}
	private static void hanoiHelper(HanoiArgs argsIn, final int exp2_ndisks,
			int[][] arr) {
		int idx = argsIn.acc - 1;
		
		if (0 == argsIn.ndisks)
			return;
		HanoiArgs ha1 = new HanoiArgs(argsIn.src, argsIn.spare, argsIn.dest,
			argsIn.ndisks - 1, 
			argsIn.acc - exp2_ndisks / (int)Math.pow(2.0f, argsIn.splits + 1),
			argsIn.splits + 1);
		HanoiArgs ha2 = new HanoiArgs(argsIn.spare, argsIn.dest, argsIn.src,
			argsIn.ndisks - 1, 
			argsIn.acc + exp2_ndisks / (int)Math.pow(2.0f, argsIn.splits + 1),
			argsIn.splits + 1);
		arr[idx][0] = argsIn.src;
		arr[idx][1] = argsIn.dest;
		hanoiHelper(ha1, exp2_ndisks, arr);
		hanoiHelper(ha2, exp2_ndisks, arr);
	}
	
	public static int[][] hanoi(final int src, final int dest, final int spare,
			final int ndisks) {
		final int exp2_ndisks = (int)Math.pow(2.0f, ndisks);
		final int len_arr = exp2_ndisks - 1, splits = 1;
		
		int[][] arr = new int[len_arr][];
		for (int i = 0; len_arr > i; ++i)
			arr[i] = new int[2];
		
		HanoiArgs argsIn = new HanoiArgs(src, dest, spare, ndisks, 
			exp2_ndisks / (int)Math.pow(2.0f, splits), splits);
		hanoiHelper(argsIn, exp2_ndisks, arr);
		return arr;
	}
	
	private static boolean safep(final int r, final int c, final int col,
			final int[] board) {
		return (board[c] == r) || (Math.abs(board[c] -r) == col - c);
	}
	private static int nqueensHelper(int queensNdx, final int numqueens, 
			final int col, int[] board) {
		if (numqueens == col)
			return queensNdx - 1;
		for (int r = 0, c = 0; numqueens > r; ++r) {
			for (c = 0; col > c && !safep(r, c, col, board); ++c);
			
			if (col > c)
				continue;
			board[col] = r;
			queensNdx = nqueensHelper(queensNdx, numqueens, col + 1, 
				board);
			if (0 == queensNdx)
				return queensNdx;
		}
		return queensNdx;
	}
	
	public static int[] nqueens(final int ndx, final int numqueens) {
		int board[] = new int[numqueens], queensNdx = ndx;
		queensNdx = nqueensHelper(queensNdx, numqueens, 0, board);
		return board;
	}
}
