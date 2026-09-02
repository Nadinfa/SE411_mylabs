package se411.lab3;


import java.util.Arrays;
import java.util.List;

public class App {

public static void main(String[] args) {

	String[] names = {"Nadin", "Sara", "Nora"};
	PrintableList<String> stringList = new PrintableList<>(names);
	
	System.out.println("String List:");
	stringList.printItems();
	
	Integer[] numbers = {1, 2, 3, 4};
	PrintableList<Integer> integerList = new PrintableList<>(numbers);
	
	System.out.println("Integer List:");
	integerList.printItems();
	
	
	NumberBox<Integer> integerBox = new NumberBox<>(10);
	
	System.out.println("Integer Box:");
	System.out.println("Item: " + integerBox.getItem());
	System.out.println("Sum: " + integerBox.sum(5));
	
	
	NumberBox<Double> doubleBox = new NumberBox<>(10.5);
	
	System.out.println("Double Box:");
	System.out.println("Item: " + doubleBox.getItem());
	System.out.println("Sum: " + doubleBox.sum(5.5));
	
	
	
	List<String> words = Arrays.asList("Java", "Generics", "Lab");
	
	System.out.println("Wildcard List:");
	printList(words);
	
	
	List<Integer> nums = Arrays.asList(10, 20, 30);
	
	System.out.println("Sum of Numbers:");
		System.out.println(sumNumbers(nums));
	}
	
	
	public static void printList(List<?> list) {
		for (Object item : list) {
			System.out.println(item);
		}
	}
	
	
	public static double sumNumbers(
		List<? extends Number> list) {
		
		double sum = 0;
		
		for (Number number : list) {
			sum += number.doubleValue();
		}
		
		return sum;
	}
}
