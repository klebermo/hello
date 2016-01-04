package org.hello.custom.thymeleaf.processor.nav;

import org.hello.generic.persistence.Dao;
import org.hello.generic.persistence.Model;
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
			Class<?> dao_class = Class.forName("org.hello.model."+target.toLowerCase()+"."+target+"Dao");
			Dao<?> dao = (Dao<?>) dao_class.newInstance();
			org.hello.ApplicationContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(dao);
			
			for(Object object : dao.select()) {
				Model model_object = (Model) object;
				Element li = new Element("li");
				
				Element a = new Element("a");
				a.setAttribute("href", "#");
				a.addChild(new Text(model_object.toString()));
				
				li.addChild(a);
				ol.addChild(li);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
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
