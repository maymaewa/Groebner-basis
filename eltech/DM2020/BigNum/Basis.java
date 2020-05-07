package eltech.DM2020.BigNum;

import java.util.*;

public class Basis
{
	private ArrayList<BigPolinom> polynoms = new ArrayList<BigPolinom>();
	private int maxpower;
	
	public void addBasis(String newPolynom)
	{
		polynoms.add( new BigPolinom(maxpower, newPolynom) );
	}
	
	public void setMaxPower(int power)
	{
		maxpower = power;
	}
	
	public void doActions()
	{
		this.sPolynom();
		this.simple();
		this.removeDivided();
		this.output();
	}
	
	public void output()
	{
		int i;
		System.out.println("Размер базиса: " + this.polynoms.size());
		for(i = 0; i < this.polynoms.size(); i++)
			System.out.println(this.polynoms.get(i) + "\n");
	}
	
	private void removeDivided()
	{
		int i,j;
		for(i = 0; i < this.polynoms.size()-1; i++)
		{
			for(j = 0; j < this.polynoms.size(); j++)
			{
				if(i != j)
					if(this.polynoms.get(i).equals2(this.polynoms.get(j)))
						this.polynoms.remove(i);
			}
		}
	}
	
	private void simple()
	{
		int i;
		for(i = 0; i < this.polynoms.size(); i++)				//Упрощаем базисы
		{
			this.polynoms.set(i, this.polynoms.get(i).reduce2(this.polynoms));
			//System.out.println("TEST " + this.polynoms.get(i).isZero() + " i = " + i);
			if(this.polynoms.get(i).isZero())
			{
				this.polynoms.remove(i);
				i--;
			}
		}
	}
	
	private void sPolynom()
	{
		int i,j;
		for(i = 0; i < this.polynoms.size(); i++)
			for(j = i+1; j < this.polynoms.size(); j++)
			{
				//System.out.println(i + ", " + j);
				this.polynoms.get(i).sPolynom( this.polynoms.get(j) ).reduce(this.polynoms);
			}
	}
}


/*
	3
	x1x2-x3^2-x3
	x1^2-x1-x2x3
	x1x3-x2^2-x2

	3
	x1x2-x3^2-x3
	x1^2+x1-x2x3
	x1x3-x2^2-x2
	
	2
	x2^2-1
	x1x2-1

	3
	3x1^3x2x3-x1x2+x1-x3
	x1x2-x2^2+x3
	5x2^3-x3

*/