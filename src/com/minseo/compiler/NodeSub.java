package com.minseo.compiler;

public class NodeSub extends Node {

	@Override
	public void Optimise() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String Generate() {
		// TODO Auto-generated method stub
		return left.Generate()+"-"+right.Generate();
	}

}
