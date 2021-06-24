package kr.or.ddit.designpattern.templetmethod;

public class ConcreteClass1 extends TemplateClass{

	@Override
	protected void secondStep() {
		System.out.println("두번째 단계 - 하위에서 해야하는 일이 각기 달라질 수 있음");
	}

}
