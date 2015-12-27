package org.hello.custom.thymeleaf.processor.field;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.hello.custom.annotations.form.SelectList;
import org.hello.generic.persistence.Dao;
import org.hello.generic.persistence.Model;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Attribute;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Text;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.element.AbstractElementProcessor;

import antlr.collections.List;

public class SelectProcessor extends AbstractElementProcessor {

	protected SelectProcessor() {
		super("select");
	}

	@Override
	protected ProcessorResult processElement(Arguments arguments, Element element) {
		Locale currentLocale = Locale.getDefault();
		ResourceBundle messages = ResourceBundle.getBundle("messages", currentLocale);
		Field field = (Field) arguments.getLocalVariable("field");
		
		Element node = new Element("select");
		node.setAttribute("name", field.getName());
		
		if(field.getType().equals(List.class))
			node.setAttribute("multiple", "multiple");
		
		for( Map.Entry<String, Attribute> entry : element.getAttributeMap().entrySet() )
		      node.setAttribute(entry.getKey(), entry.getValue().getValue());
		
		if(field.getType().isPrimitive()) {
			String[] list = field.getAnnotation(SelectList.class).list();
			if(list != null) {
				for(String object : list) {
					Element option = new Element("option");
					option.setAttribute("value", object);
					option.addChild(new Text(messages.getString(object)));
					node.addChild(option);
				}
			}
		} else {
			try {
				Class<?> clazz = Class.forName("org.hello.model." + field.getName() + "." + field.getName() + "Dao");
				Dao dao = (Dao) clazz.newInstance();
				org.hello.ApplicationContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(dao);
				for(Object object : dao.select()) {
					Element option = new Element("option");
					Model model = (Model) object;
					option.setAttribute("value", model.getId());
					option.addChild(new Text(messages.getString(model.toString())));
					node.addChild(option);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		element.getParent().insertBefore(element, node);
		element.getParent().removeChild(element);
		
		return ProcessorResult.OK;
	}

	@Override
	public int getPrecedence() {
		return 0;
	}

}
