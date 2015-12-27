package org.hello.custom.thymeleaf.processor.nav;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Text;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.element.AbstractElementProcessor;

public class UlProcessor extends AbstractElementProcessor {

	public UlProcessor() {
		super("ul");
	}

	@Override
	public ProcessorResult processElement(Arguments arguments, Element element) {
		String target = element.getAttributeValue("target");
		
		Element ol = new Element("ol");
		ol.setProcessable(true);
		try {
			for(Class<?> clazz : org.hello.Application.getListaClasses(target)) {
				Element li = new Element("li");
				
				Element a = new Element("a");
				a.setAttribute("href", "#");
				a.addChild(new Text(clazz.getSimpleName()));
				
				li.addChild(a);
				ol.addChild(li);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		element.getParent().insertBefore(element, ol);
		element.getParent().removeChild(element);
		
		return ProcessorResult.OK;
	}

	@Override
	public int getPrecedence() {
		return 0;
	}

}
