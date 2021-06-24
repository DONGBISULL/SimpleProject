package kr.or.ddit.designpattern.templetmethod;

public abstract class TemplateClass { //추상 메서드 있을 것 추정
	
	public final void template() { //final 오버라이딩 못함 순서를 바꿀 수 없다 
		
		firstStep();
		secondStep(); //얘가 구현되지 않아서 인스턴스 생성 불가
		thirdStep();
	}
	
	private void firstStep() {
		System.out.println("첫번째 단계");
	}

	protected abstract void secondStep(); 
	
	private void thirdStep() {
		System.out.println("세번째 단계");
	}

}
