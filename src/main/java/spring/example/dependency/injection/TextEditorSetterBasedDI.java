package spring.example.dependency.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextEditorSetterBasedDI {
	
	private SpellChecker spellChecker;

	@Autowired
	// a setter method to inject the dependency.
	public void setSpellChecker(SpellChecker spellChecker) {
		System.out.println("Inside setSpellChecker." );
		this.spellChecker = spellChecker;
	}
	
	// a getter method to return spellChecker
	public SpellChecker getSpellChecker() {
		return spellChecker;
	}

	public void spellCheck() {
		System.out.println("Inside TextEditorSetterBasedDI.spellCheck()." );
		spellChecker.checkSpelling();
	}
}
