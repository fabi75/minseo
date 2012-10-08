package com.minseo.compiler;

public abstract class Node 
{
	static final int tInt=0;
	static final int tString=1;
	static final int tFloat=2;
	static final int tRVB=3;

	static final boolean tValue=false;
	static final boolean tVar=true;

	Node left;
	Node right;
	public int type;
	public boolean var;
	Object val;
	
	public Node()
	{
	}
	
	public abstract void Optimise();
	public abstract String Generate();
}
