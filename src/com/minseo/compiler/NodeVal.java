package com.minseo.compiler;

public class NodeVal extends Node {

	@Override
	public void Optimise() 
	{
	}

	@Override
	public String Generate()
	{
		switch(type)
		{
		case tInt:
			return ((Integer)val).toString();
		case tFloat:
			return ((Float)val).toString();
		case tString:
			return "\""+val.toString()+"\"";
		}
	return "";
	}

}
