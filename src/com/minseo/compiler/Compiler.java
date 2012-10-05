package com.minseo.compiler;

import java.util.ArrayList;
import java.util.Arrays;

public class Compiler {

	private static final String[] tabToken={"include","for","to","next","while","do","loop","function","class","if","then","else"};
	public static final ArrayList<String> lToken=new ArrayList<>(Arrays.asList(tabToken));
	private static final String[] tabType={"int","float","string","rvb","boolean"};
	public static final ArrayList<String> lType=new ArrayList<>(Arrays.asList(tabType));
	public static final String[] tabExpr={"+","-","++","--","*","%","/","==","=","!=","<>","(",")"};
	public static final ArrayList<String> lExpr=new ArrayList<>(Arrays.asList(tabExpr));
	
	
	
	public Compiler() 
	{
	
	}
	
	protected String GetOneToken(String s)
	{
		int i=s.indexOf(' ');
		int j=s.indexOf('\t');
		i=i>j?j:i;
		s=s.substring(0,i);
		for(String test:lExpr )
			if (s.indexOf(test)>-1)
					{
					i=i>s.indexOf(test)?s.indexOf(test):i;
					}
		s=s.substring(0,i);
		return s; 
	}
	
	protected String DeleteOneToken(String s)
	{
		int i=s.indexOf(' ');
		int j=s.indexOf('\t');
		i=i>j?j:i;
		s=s.substring(0,i);
		for(String test:lExpr )
			if (s.indexOf(test)>-1)
					{
					i=i>s.indexOf(test)?s.indexOf(test):i;
					}
		return s.substring(i);
	}
	
	public String preprocessor(String text)
	{
		StringBuilder s=new StringBuilder(text);
		while(s.indexOf("//")!=-1)
		{
			int i=s.indexOf("//");
			int j=s.indexOf("\n", i);
			if (j==-1)
				j=s.length();
			s=s.replace(i, j, "");
		}
		while(s.indexOf("/*")!=-1)
		{
			int i=s.indexOf("/*");
			int j=s.indexOf("*/", i);
			s=s.replace(i, j+2, "");
		}
		String compiled=s.toString();
		return compiled;		
	}
	
	public String ReadExpression(String s)
	{
		return s;
	}
	
	public String ReadDeclaration(String s)
	{
		String result="";
		String word=GetOneToken(s);
		s=DeleteOneToken(s);
		result="var "+GetOneToken(s);
		s=DeleteOneToken(s);
		word=GetOneToken(s);
		if (word=="=")
		{
			s=DeleteOneToken(s);
			result+="="+ReadExpression(s);
		}
		return result;
	}

	public String ReadToken(String s)
	{
		String result="";
		String word=GetOneToken(s);
		s=DeleteOneToken(s);
		result="var "+GetOneToken(s);
		s=DeleteOneToken(s);
		if (s.indexOf("=")>-1)
			result+=ReadExpression(s);
		return result;
	}
	
	public String compileOneLine(String text)
	{
		String result="";
		// first word
		String word=GetOneToken(text);
		if (lType.indexOf(word)!=-1)
		{
			return ReadDeclaration(text);
		}
		else if (lToken.indexOf(word)!=-1)
		{
			return ReadToken(text);
		}
		return result;
	}
	
	public String compile(String text)
	{
		String compiled="";
		String line;
		StringBuilder s=new StringBuilder(text);
		while(s.indexOf("\n")!=-1)
		{
			int i=s.indexOf("\n");
			line=s.substring(0,i);
			s=s.replace(0, i+1, "");
			line=line+";";
			compiled=compiled+line;
		}
		return compiled;
	}
	
	public String postprocessor(String text)
	{
		return text.replace('\n', ' ').replace('\r', ' ');		
		
	}
	
	public String Process(String text)
	{
		String compiled=preprocessor(text);
		compiled=compile(compiled);
		compiled=postprocessor(compiled);
		return compiled;
	}
}
