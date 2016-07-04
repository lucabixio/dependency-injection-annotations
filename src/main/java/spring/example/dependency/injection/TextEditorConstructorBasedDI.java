package spring.example.dependency.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextEditorConstructorBasedDI {

	private SpellChecker spellChecker;
	
	@Autowired
	public TextEditorConstructorBasedDI(SpellChecker spellChecker) {
		super();
		this.spellChecker = spellChecker;
	}
	
	public void spellCheck() {
		System.out.println("Inside TextEditorConstructorBasedDI.spellCheck()." );
		spellChecker.checkSpelling();
	}
	
}
