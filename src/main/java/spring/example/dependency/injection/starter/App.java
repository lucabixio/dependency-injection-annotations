package spring.example.dependency.injection.starter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.example.dependency.injection.TextEditorConfig;
import spring.example.dependency.injection.TextEditorConstructorBasedDI;
import spring.example.dependency.injection.TextEditorSetterBasedDI;

public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
			      new AnnotationConfigApplicationContext(TextEditorConfig.class);

		TextEditorSetterBasedDI tesb = context.getBean(TextEditorSetterBasedDI.class);
		
		TextEditorConstructorBasedDI tecb = context.getBean(TextEditorConstructorBasedDI.class);
		
		tesb.spellCheck();
		
		tecb.spellCheck();
		
		context.close();
		
	}

}
