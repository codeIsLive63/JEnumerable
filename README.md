# JEnumerable

JEnumerable — это фреймворк для работы с коллекциями, реализующий LINQ-подобный API на Java. Он предоставляет удобные методы для обработки данных, а также операции преобразования коллекций в потоки и массивы.

## Features and Functionality

*   **LINQ-подобный API:** Предоставляет методы, аналогичные LINQ, для работы с коллекциями в Java.
*   **`from()`:** Создает `JEnumerable` из `Enumerable`, массива, `Iterable`.
*   **`select()`:** Проецирует каждый элемент коллекции в новую форму. Поддерживает проекцию с учетом индекса.
*   **`selectMany()`:**  Проецирует каждый элемент коллекции в другую коллекцию и объединяет результаты. Поддерживает проекцию с учетом индекса и селектор результата.
*   **`where()`:** Фильтрует элементы коллекции на основе заданного условия. Поддерживает фильтрацию с учетом индекса.
*   **`count()`:** Подсчитывает количество элементов в коллекции, как общее, так и удовлетворяющих условию.
*   **`take()`:** Возвращает указанное количество элементов с начала коллекции.
*   **`toList()`:** Преобразует `JEnumerable` в `List`.
*   **`toArray()`:** Преобразует `JEnumerable` в массив.
*   **`asStream()`:** Преобразует `JEnumerable` в `Stream`.
*   **`asParallelStream()`:** Преобразует `JEnumerable` в параллельный `Stream`.
*   **`any()`:** Проверяет, содержит ли коллекция хотя бы один элемент, удовлетворяющий заданному условию.
*   **`all()`:** Проверяет, удовлетворяют ли все элементы коллекции заданному условию.
*   **Fluent API:** Позволяет строить цепочки операций над коллекциями.

## Technology Stack

*   Java 8+

## Prerequisites

*   Java Development Kit (JDK) 8 or higher.

## Installation Instructions

1.  Clone the repository:

    ```bash
    git clone https://github.com/codeIsLive63/JEnumerable.git
    ```

2.  Navigate to the project directory:

    ```bash
    cd JEnumerable
    ```

3.  Build the project (using your preferred build tool like Maven or Gradle).  Since the provided code doesn't include build scripts, you'll need to create your own based on your project setup.  For example, with Maven:

    ```bash
    mvn clean install
    ```

    (You'll need to create a `pom.xml` file in the project root.  A basic `pom.xml` is not included.)

## Usage Guide

Here are some examples of how to use JEnumerable:

**Example 1: Flattening a Nested List**

```java
import codeislive63.collections.generic.List;
import codeislive63.jenumerable.JEnumerable;

public class Main {
    public static void main(String[] args) {
        List<List<List<Integer>>> nestedList = new List<>() {{
            add(new List<>() {{
                add(new List<>(1, 2, 3, 4));
            }});

            add(new List<>() {{
                add(new List<>(5, 6, 7));
            }});

            add(new List<>() {{
                add(new List<>(8, 9, 10));
            }});
        }};

        JEnumerable.from(nestedList)
                .selectMany(list -> JEnumerable.from(list).selectMany(innerList -> innerList))
                .forEach(e -> System.out.print(e + " "));
        System.out.println("\n");
    }
}
```

**Example 2:  Selecting Favorite Languages**

```java
import codeislive63.collections.generic.List;
import codeislive63.jenumerable.JEnumerable;
import codeislive63.Person; // Assuming Person class is defined

public class Main {
    public static void main(String[] args) {
        List<Person> peoples = new List<>(
                new Person("Nikita", 19, "C#", "JavaScript", "Java", "C++"),
                new Person("Alexey", 26, "Go", "C", "Ruby", "Python"),
                new Person("Andrey", 20, "Python", "PHP", "Assembler")
        );

        JEnumerable.from(peoples)
                .selectMany(Person::getFavoriteLanguages)
                .forEach(System.out::println);
        System.out.println();
    }
}
```

**Example 3: Using `count` and `take`**

```java
import codeislive63.collections.generic.List;
import codeislive63.jenumerable.JEnumerable;
import codeislive63.Person; // Assuming Person class is defined

public class Main {
    public static void main(String[] args) {
        List<Person> peoples = new List<>(
                new Person("Nikita", 19, "C#", "JavaScript", "Java", "C++"),
                new Person("Alexey", 26, "Go", "C", "Ruby", "Python"),
                new Person("Andrey", 20, "Python", "PHP", "Assembler")
        );

        System.out.println(JEnumerable.from(peoples).count(p -> p.getAge() <= 20));
        JEnumerable.from(peoples).take(2).forEach(x -> System.out.println(x.getName()));
    }
}
```

These examples demonstrate the basic usage of `JEnumerable` for common collection manipulation tasks.  Refer to the `src/codeislive63/Main.java` file for more complete examples.

## API Documentation

### JEnumerable Class

*   **`static <TCollection> JEnumerable<TCollection> from(Enumerable<TCollection> collection)`**: Creates a `JEnumerable` from an `Enumerable` object.
*   **`static <TCollection> JEnumerable<TCollection> from(TCollection... collection)`**: Creates a `JEnumerable` from a variable number of arguments (array).
*   **`static <TCollection> JEnumerable<TCollection> from(Iterable<TCollection> collection)`**: Creates a `JEnumerable` from an `Iterable` collection.
*   **`<TResult> JEnumerable<TResult> select(Func<TSource, TResult> selector)`**: Projects each element of a sequence into a new form using a selector function.
*   **`<TResult> JEnumerable<TResult> select(BiFunc<TSource, Integer, TResult> selector)`**: Projects each element of a sequence into a new form by incorporating the element's index, using a selector function.
*   **`<TResult> JEnumerable<TResult> selectMany(Func<TSource, Enumerable<TResult>> selector)`**: Projects each element of a sequence to an `Enumerable<TResult>` and flattens the resulting sequences into one sequence.
*   **`<TResult> JEnumerable<TResult> selectMany(BiFunc<TSource, Integer, Enumerable<TResult>> selector)`**: Projects each element of a sequence to an `Enumerable<TResult>` by incorporating the element's index and flattens the resulting sequences into one sequence.
*   **`<TCollection, TResult> JEnumerable<TResult> selectMany(Func<TSource, Enumerable<TCollection>> collectionSelector, BiFunc<TSource, TCollection, TResult> resultSelector)`**: Projects each element of a sequence to an `Enumerable<TCollection>` and invokes a result selector function on each element of the resulting sequence.
*   **`<TCollection, TResult> JEnumerable<TResult> selectMany(BiFunc<TSource, Integer, Enumerable<TCollection>> collectionSelector, BiFunc<TSource, TCollection, TResult> resultSelector)`**: Projects each element of a sequence to an `Enumerable<TCollection>` by incorporating the element's index and invokes a result selector function on each element of the resulting sequence.
*   **`JEnumerable<TSource> where(Predicate<TSource> predicate)`**: Filters a sequence of values based on a predicate.
*   **`JEnumerable<TSource> where(BiFunc<TSource, Integer, Boolean> predicate)`**: Filters a sequence of values based on a predicate, where each element's index is also used in the predicate.
*   **`int count()`**: Returns the number of elements in a sequence.
*   **`int count(Func<TSource, Boolean> predicate)`**: Returns the number of elements in a sequence that satisfy a condition.
*   **`JEnumerable<TSource> take(int count)`**: Returns a specified number of contiguous elements from the start of a sequence.
*   **`List<TSource> toList()`**: Converts the `JEnumerable` sequence to a `List`.
*   **`TSource[] toArray()`**: Converts the `JEnumerable` sequence to an array.
*   **`Stream<TSource> asStream()`**: Converts the `JEnumerable` sequence to a sequential stream.
*   **`Stream<TSource> asParallelStream()`**: Converts the `JEnumerable` sequence to a parallel stream.
*   **`boolean any(Predicate<TSource> predicate)`**: Determines whether any element of a sequence satisfies a condition.
*   **`boolean all(Predicate<TSource> predicate)`**: Determines whether all elements of a sequence satisfy a condition.
*   **`Enumerator<TSource> getEnumerator()`**: Returns an enumerator that iterates through the collection.

### Interfaces

*   **`Enumerable<T>`**: Represents a collection that can be enumerated.
*   **`Collection<T>`**: Extends `Enumerable` and provides methods for adding, removing, and clearing elements.
*   **`ModifiableList<T>`**: Extends `Collection` and allows accessing elements by index, supporting modification operations.
*   **`Enumerator<T>`**: Provides methods for iterating through a collection.

### Delegates

Several functional interfaces are defined in the `codeislive63.delegates` and `codeislive63.delegates.generic` packages, including:

*   **`Action<T>`**: Represents an action that takes one argument.
*   **`AnyAction`**: Represents an action that takes no arguments.
*   **`Func<T1, TResult>`**: Represents a function that takes one argument and returns a result.
*   **`AnyFunc<TResult>`**: Represents a function that takes no arguments and returns a result.
*   **`BiFunc<T1, T2, TResult>`**: Represents a function that takes two arguments and returns a result.
*   **`Predicate<T>`**: Represents a function that takes one argument and returns a boolean value.

## Contributing Guidelines

Contributions are welcome! To contribute to JEnumerable, please follow these steps:

1.  Fork the repository.
2.  Create a new branch for your feature or bug fix.
3.  Implement your changes and write tests.
4.  Ensure that all tests pass.
5.  Submit a pull request with a clear description of your changes.

## License Information

License is not specified.

## Contact/Support Information

For questions, bug reports, or feature requests, please open an issue on the [GitHub repository](https://github.com/codeIsLive63/JEnumerable).