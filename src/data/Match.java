package data;

import java.util.Scanner;

public class Match {
	private Team A;
	private Team B;
	
	public Match(Scanner s, Team T1,Team T2) {
		this.A = T1;
		this.B = T2;
		this.enterResult(s,A,B);
	}
	
	private void enterResult (Scanner s,Team a, Team b) {
		System.out.println( "Enter the result of "+a + "vs. " +b);
		System.out.println(a+ " scored:");
	    int ascore = s.nextInt();
	    System.out.println(b+ " scored:");
	    int bscore = s.nextInt();
	    compute(ascore, bscore);
	    System.out.println(a + " received "+a.getPoints() + " Points");
	    System.out.println(b + " received "+b.getPoints() + " Points");
	}
	
	private void compute(int a, int b) {
		if(a>b) {
			this.A.addPoints(3);
			this.B.addPoints(0);
		}
		else if(a<b) {
			this.A.addPoints(0);
			this.B.addPoints(3);
		}else {
			this.A.addPoints(1);
			this.B.addPoints(1);
		}

	}

}
