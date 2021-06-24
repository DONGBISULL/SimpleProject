package kr.or.ddit.marshalling;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.FactorialVO;

public class XmlMarshallingExample {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException, JAXBException {
		FactorialVO target = new FactorialVO(10);

		JAXBContext context = JAXBContext.newInstance(FactorialVO.class);//클래스를 달라 --> 마샬링할 클래스 주며 ㄴ됨
	/*	Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(target, System.out);
		*/

		Unmarshaller unmarshaller =  context.createUnmarshaller();
try(
		InputStream is = XmlMarshallingExample.class.getResourceAsStream("factorial.xml");
		){
	FactorialVO resultObj = (FactorialVO) unmarshaller.unmarshal(is);
	System.out.println(resultObj);
}
//도비를 집에 보내주세요 ㅠㅠ
	
	
	}
	
	
	public static void marshalliongToJson(FactorialVO target) throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		//mapper.writeValue(System.out, target);
		//res ==> classPath
	try(
			InputStream src = XmlMarshallingExample.class.getResourceAsStream("factorial.json");
			){
		FactorialVO resultObj = mapper.readValue(src, FactorialVO.class);
		System.out.println(resultObj);
	}
	
	}
}
