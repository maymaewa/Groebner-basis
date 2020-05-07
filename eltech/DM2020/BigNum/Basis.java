package eltech.DM2020.BigNum;

import java.util.*;

public class Basis
{
	private ArrayList<BigPolinom> polynoms = new ArrayList<BigPolinom>();
	private ArrayList<BigPolinom> basePolynoms = new ArrayList<BigPolinom>();
	private ArrayList<String> linked = new ArrayList<String>();	//Записываем те многочлены, с которыми уже строили S полином
	private int maxpower;
	
	public void addBasis(String newPolynom)
	{
		polynoms.add( new BigPolinom(maxpower, newPolynom) );
		basePolynoms.add( new BigPolinom(maxpower, newPolynom) );
		linked.add("");
	}
	
	public void setMaxPower(int power)
	{
		maxpower = power;
	}
	
	public void doActions()
	{
		linked.remove(linked.size()-1);
		this.sPolynom();
		//this.sPolynom2();
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
					//if(this.polynoms.get(i).getHighMonom().isDivided(this.polynoms.get(j).getHighMonom()))
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
		int k;
		Integer i,j;
		for(i = 0; i < this.basePolynoms.size(); i++)
			for(j = i+1; j < this.basePolynoms.size(); j++)
			{
				//System.out.println(this.isLinked(i,j));
				this.basePolynoms.get(i).sPolynom( this.basePolynoms.get(j) ).reduce(this.polynoms);
			}
	}
	
	private void sPolynom2()
	{
		Integer i,j;
		i = 0; j = 0;
		while(i < this.polynoms.size()-1)
		{
			j = i+1;
			while(j < this.polynoms.size())
			{
				System.out.println(this.isLinked(i,j));
				if(!this.isLinked(i,j))
				{
					this.polynoms.get(i).sPolynom( this.polynoms.get(j) ).reduce(this.polynoms);
					i = 0; j = 1;
				}
				j++;
			}
			i++;
		}
	}
	
	private boolean isLinked(Integer ths, Integer other)		//Проверка на связку
	{
		String buffS = other.toString();
		String buffLink = linked.get(ths);
		//System.out.println(buffS + " : " + buffLink);
		if(buffLink.indexOf(buffS) == -1)
		{
			if(buffLink.equals(""))
				buffLink = buffS;
			else
				buffLink += "," + buffS;
			linked.set(ths, buffLink);
			return false;
		}
		return true;
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
	x1^2x2x3-x1x2+x1-x3
	x1x2-x2^2+x3
	x2^2-x3

*/