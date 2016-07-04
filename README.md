# dependency-injection-annotations

This example shows how to deal with Dependency Injection (DI) in Spring MVC by using Annotations.

Every java based application has a few objects that work together to present what the end-user sees as a working application. When writing a complex Java application, application classes should be as independent as possible of other Java classes to increase the possibility to reuse these classes and to test them independently of other classes while doing unit testing. Dependency Injection (or sometime called wiring) helps in gluing these classes together and same time keeping them independent.

Consider you have an application which has a text editor component and you want to provide spell checking. Your standard code would look something like this:

```java
public class TextEditor {
   private SpellChecker spellChecker;
   
   public TextEditor() {
      spellChecker = new SpellChecker();
   }
}
```


What we've done here is create a dependency between the TextEditor and the SpellChecker. In an inversion of control scenario we would instead do something like this:

```java
public class TextEditor {
   private SpellChecker spellChecker;
   
   public TextEditor(SpellChecker spellChecker) {
      this.spellChecker = spellChecker;
   }
}
```

Here TextEditor should not worry about SpellChecker implementation. The SpellChecker will be implemented independently and will be provided to TextEditor at the time of TextEditor instantiation and this entire procedure is controlled by the Spring Framework.

Here, we have removed the total control from TextEditor and kept it somewhere else (i.e., java configuration file) and the dependency (i.e., class SpellChecker) is being injected into the class TextEditor through a Class Constructor. Thus flow of control has been "inverted" by Dependency Injection (DI) because you have effectively delegated dependences to some external system.

Second method of injecting dependency is through Setter Methods of TextEditor class where we will create SpellChecker instance and this instance will be used to call setter methods to initialize TextEditor's properties.

## Constructor based DI
Constructor-based DI is accomplished when the container invokes a class constructor with a number of arguments, each representing a dependency on other class.

Example:
The following example shows a class TextEditor that can only be dependency-injected with constructor injection.

Here is the content of TextEditor.java file:

```java
package spring.example.dependency.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextEditor {

	private SpellChecker spellChecker;
	
	@Autowired
	public TextEditor(SpellChecker spellChecker) {
		super();
		this.spellChecker = spellChecker;
	}
	
	public void spellCheck() {
		System.out.println("Inside TextEditor.spellCheck()." );
		spellChecker.checkSpelling();
	}
	
}
```

Following is the content of another dependent class file SpellChecker.java:

```java
package spring.example.dependency.injection;

import org.springframework.stereotype.Service;

@Service
public class SpellChecker {
	
	public SpellChecker(){
		System.out.println("Inside SpellChecker constructor." );
	}

	public void checkSpelling() {
		System.out.println("Inside checkSpelling." );
	}
}
```

Following is the content of the MainApp.java file:

```java
package spring.example.dependency.injection.starter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.example.dependency.injection.TextEditorConfig;
import spring.example.dependency.injection.TextEditor;

public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
			      new AnnotationConfigApplicationContext(TextEditorConfig.class);

		TextEditor tecb = context.getBean(TextEditor.class);
		
		tecb.spellCheck();
		
		context.close();
		
	}

}
```

Following is the configuration file TextEditorConfig.java which has configuration for the constructor-based injection:

```java
package spring.example.dependency.injection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TextEditorConfig {

	@Bean 
	public TextEditor textEditor(){
		return new TextEditor(spellChecker());
	}
	
	@Bean 
	public SpellChecker spellChecker(){
		return new SpellChecker();
	}

}
```

Once you are done with creating source and bean configuration files, let us run the application. If everything is fine with your application, this will print the following message:

```sh
Inside SpellChecker constructor.
Inside TextEditor constructor.
Inside checkSpelling.
```

## Setter based DI
Setter-based DI is accomplished by the container calling setter methods on your beans after invoking a no-argument constructor or no-argument static factory method to instantiate your bean.

Example:
The following example shows a class TextEditor that can only be dependency-injected using pure setter-based injection.
Here is the content of TextEditor.java file:

```java
package spring.example.dependency.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextEditor {
	
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
```
Here you need to check naming convention of the setter methods. To set a variable spellChecker we are using setSpellChecker() method which is very similar to Java POJO classes. Let us create the content of another dependent class file SpellChecker.java:

```java
package spring.example.dependency.injection;

public class SpellChecker {
   public SpellChecker(){
      System.out.println("Inside SpellChecker constructor." );
   }

   public void checkSpelling() {
      System.out.println("Inside checkSpelling." );
   }
   
}
```

Following is the content of the MainApp.java file:

```java
package spring.example.dependency.injection.starter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.example.dependency.injection.TextEditorConfig;
import spring.example.dependency.injection.TextEditor;

public class App {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
			      new AnnotationConfigApplicationContext(TextEditorConfig.class);

		TextEditor tesb = context.getBean(TextEditor.class);
		
		tesb.spellCheck();
		
		context.close();
		
	}

}
```

Following is the configuration file TextEditorConfig.java which has configuration for the setter-based injection:

```java
package spring.example.dependency.injection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TextEditorConfig {
	
	@Bean 
	public TextEditor textEditor(){
		return new TextEditor();
	}

	@Bean 
	public SpellChecker spellChecker(){
		return new SpellChecker();
	}

}

```

Once you are done with creating source and java configuration files, let us run the application. If everything is fine with your application, this will print the following message:

```sh
Inside SpellChecker constructor.
Inside setSpellChecker.
Inside checkSpelling.
```