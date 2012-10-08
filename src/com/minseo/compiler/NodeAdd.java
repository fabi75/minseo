package com.minseo.compiler;

public class NodeAdd extends Node 
{
	
	@Override
	public void Optimise()
	{
		switch(left.type)
		{
		case tInt:
			switch(right.type)
			{
			case tInt:
				break;
			case tFloat:
				break;
			case tString:
				break;		
			}
			break;
		case tFloat:
			switch(right.type)
			{
			case tInt:
				break;
			case tFloat:
				break;
			case tString:
				break;		
			}
			break;
		case tString:
			switch(right.type)
			{
			case tInt:
				break;
			case tFloat:
				break;
			case tString:
				break;		
			}
			break;		
		}
	}

	@Override
	public String Generate()
		{
		return left.Generate()+"+"+right.Generate();
		}
}
