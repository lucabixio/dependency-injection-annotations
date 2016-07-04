package spring.example.dependency.injection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TextEditorConfig {

	@Bean 
	public TextEditorConstructorBasedDI textEditorConstructorBasedDI(){
		return new TextEditorConstructorBasedDI(spellChecker());
	}
	
	@Bean 
	public TextEditorSetterBasedDI textEditorSetterBasedDI(){
		return new TextEditorSetterBasedDI();
	}

	@Bean 
	public SpellChecker spellChecker(){
		return new SpellChecker();
	}

}
