package com.designpatterns.demo;
import java.util.*;

public class ConsolidatedDesignPatterns {

    public static void main(String[] args) {
        demonstrateObserver();
        demonstrateStrategy();
        demonstrateSingleton();
        demonstrateFactoryMethod();
        demonstrateAdapter();
        demonstrateDecorator();
    }

    // Observer Pattern
    private static void demonstrateObserver() {
        System.out.println("\n--- Observer Pattern Demonstration ---");
        WeatherStation weatherStation = new WeatherStation();
        WeatherDisplay display = new WeatherDisplay();
        weatherStation.addObserver(display);
        weatherStation.setMeasurements(25.5f, 65.0f, 1013.1f);
    }

    interface Observer {
        void update(float temperature, float humidity, float pressure);
    }

    static class WeatherStation {
        private List<Observer> observers = new ArrayList<>();
        private float temperature;
        private float humidity;
        private float pressure;

        public void addObserver(Observer observer) {
            observers.add(observer);
        }

        public void setMeasurements(float temperature, float humidity, float pressure) {
            this.temperature = temperature;
            this.humidity = humidity;
            this.pressure = pressure;
            notifyObservers();
        }

        private void notifyObservers() {
            for (Observer observer : observers) {
                observer.update(temperature, humidity, pressure);
            }
        }
    }

    static class WeatherDisplay implements Observer {
        @Override
        public void update(float temperature, float humidity, float pressure) {
            System.out.println("Weather Update: Temperature " + temperature + 
                               "°C, Humidity " + humidity + "%, Pressure " + pressure + " hPa");
        }
    }

    // Strategy Pattern
    private static void demonstrateStrategy() {
        System.out.println("\n--- Strategy Pattern Demonstration ---");
        SortContext context = new SortContext();
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        
        context.setStrategy(new BubbleSort());
        context.sortArray(array.clone());
        
        context.setStrategy(new QuickSort());
        context.sortArray(array.clone());
    }

    interface SortStrategy {
        void sort(int[] array);
    }

    static class BubbleSort implements SortStrategy {
        @Override
        public void sort(int[] array) {
            System.out.println("Sorting array using Bubble Sort");
            int n = array.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }
    }

    static class QuickSort implements SortStrategy {
        @Override
        public void sort(int[] array) {
            System.out.println("Sorting array using Quick Sort");
            quickSort(array, 0, array.length - 1);
        }

        private void quickSort(int[] array, int low, int high) {
            if (low < high) {
                int pi = partition(array, low, high);
                quickSort(array, low, pi - 1);
                quickSort(array, pi + 1, high);
            }
        }

        private int partition(int[] array, int low, int high) {
            int pivot = array[high];
            int i = (low - 1);
            for (int j = low; j < high; j++) {
                if (array[j] < pivot) {
                    i++;
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            int temp = array[i + 1];
            array[i + 1] = array[high];
            array[high] = temp;
            return i + 1;
        }
    }

    static class SortContext {
        private SortStrategy strategy;

        public void setStrategy(SortStrategy strategy) {
            this.strategy = strategy;
        }

        public void sortArray(int[] array) {
            strategy.sort(array);
            System.out.println("Sorted array: " + Arrays.toString(array));
        }
    }

    // Singleton Pattern
    private static void demonstrateSingleton() {
        System.out.println("\n--- Singleton Pattern Demonstration ---");
        ConfigurationManager config = ConfigurationManager.getInstance();
        config.setConfiguration("theme", "dark");
        config.setConfiguration("font-size", "14px");
        config.displayConfigurations();
    }

    static class ConfigurationManager {
        private static ConfigurationManager instance;
        private Map<String, String> configurations;

        private ConfigurationManager() {
            configurations = new HashMap<>();
        }

        public static ConfigurationManager getInstance() {
            if (instance == null) {
                instance = new ConfigurationManager();
            }
            return instance;
        }

        public void setConfiguration(String key, String value) {
            configurations.put(key, value);
        }

        public void displayConfigurations() {
            System.out.println("Current Configurations:");
            for (Map.Entry<String, String> entry : configurations.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    // Factory Method Pattern
    private static void demonstrateFactoryMethod() {
        System.out.println("\n--- Factory Method Pattern Demonstration ---");
        DocumentCreator pdfCreator = new PDFCreator();
        DocumentCreator wordCreator = new WordCreator();
        
        pdfCreator.editDocument();
        wordCreator.editDocument();
    }

    abstract static class Document {
        abstract void open();
        abstract void save();
    }

    static class PDFDocument extends Document {
        @Override
        void open() { System.out.println("Opening PDF document"); }
        @Override
        void save() { System.out.println("Saving PDF document"); }
    }

    static class WordDocument extends Document {
        @Override
        void open() { System.out.println("Opening Word document"); }
        @Override
        void save() { System.out.println("Saving Word document"); }
    }

    abstract static class DocumentCreator {
        abstract Document createDocument();

        public void editDocument() {
            Document doc = createDocument();
            doc.open();
            doc.save();
        }
    }

    static class PDFCreator extends DocumentCreator {
        @Override
        Document createDocument() { return new PDFDocument(); }
    }

    static class WordCreator extends DocumentCreator {
        @Override
        Document createDocument() { return new WordDocument(); }
    }

    // Adapter Pattern
    private static void demonstrateAdapter() {
        System.out.println("\n--- Adapter Pattern Demonstration ---");
        LegacyRectangle legacyRectangle = new LegacyRectangle();
        Shape rectangleAdapter = new RectangleAdapter(legacyRectangle);
        rectangleAdapter.draw(10, 20, 30, 40);
    }

    static class LegacyRectangle {
        public void drawRectangle(int x, int y, int width, int height) {
            System.out.println("Drawing Rectangle: (" + x + "," + y + ") width: " + width + " height: " + height);
        }
    }

    interface Shape {
        void draw(int x, int y, int width, int height);
    }

    static class RectangleAdapter implements Shape {
        private LegacyRectangle legacyRectangle;

        public RectangleAdapter(LegacyRectangle legacyRectangle) {
            this.legacyRectangle = legacyRectangle;
        }

        @Override
        public void draw(int x, int y, int width, int height) {
            legacyRectangle.drawRectangle(x, y, width, height);
        }
    }

    // Decorator Pattern
    private static void demonstrateDecorator() {
        System.out.println("\n--- Decorator Pattern Demonstration ---");
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println(simpleCoffee.getDescription() + " Cost: $" + simpleCoffee.getCost());

        Coffee milkCoffee = new Milk(simpleCoffee);
        System.out.println(milkCoffee.getDescription() + " Cost: $" + milkCoffee.getCost());

        Coffee sugarMilkCoffee = new Sugar(new Milk(simpleCoffee));
        System.out.println(sugarMilkCoffee.getDescription() + " Cost: $" + sugarMilkCoffee.getCost());
    }

    interface Coffee {
        String getDescription();
        double getCost();
    }

    static class SimpleCoffee implements Coffee {
        @Override
        public String getDescription() { return "Simple Coffee"; }
        @Override
        public double getCost() { return 1.0; }
    }

    abstract static class CoffeeDecorator implements Coffee {
        protected Coffee decoratedCoffee;

        public CoffeeDecorator(Coffee coffee) {
            this.decoratedCoffee = coffee;
        }

        public String getDescription() {
            return decoratedCoffee.getDescription();
        }

        public double getCost() {
            return decoratedCoffee.getCost();
        }
    }

    static class Milk extends CoffeeDecorator {
        public Milk(Coffee coffee) {
            super(coffee);
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ", Milk";
        }

        @Override
        public double getCost() {
            return super.getCost() + 0.5;
        }
    }

    static class Sugar extends CoffeeDecorator {
        public Sugar(Coffee coffee) {
            super(coffee);
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ", Sugar";
        }

        @Override
        public double getCost() {
            return super.getCost() + 0.2;
        }
    }
}
