package kr.or.ddit.designpattern;

import java.util.Arrays;
import java.util.List;

import kr.or.ddit.designpattern.templetmethod.ConcreteClass1;
import kr.or.ddit.designpattern.templetmethod.ConcreteClass2;
import kr.or.ddit.designpattern.templetmethod.TemplateClass;

public class TemplateMethodPatternTest {
	
	public static void main(String[] args) {
		List<TemplateClass> list= 
			Arrays.asList(new ConcreteClass1(),new ConcreteClass2() );//타입만 동일하면 여러개 파ㅇ라미터여도 괜찮음 
		for(TemplateClass tmp : list) {
			tmp.template();
		}
	
	}
	
	
}
