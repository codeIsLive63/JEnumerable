# JEnumerable

JEnumerable — это фреймворк для работы с коллекциями, реализующий LINQ-подобный API на Java. Он предоставляет удобные методы для обработки данных, а также операции преобразования коллекций в потоки и массивы.

## Возможности

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
*   **Совместимость с Fluent API:** Позволяет использовать цепочки операций, включая возможность работы с `Stream`.

## Технологический стек

*   Java 8+

## Инстркция по установке

1.  Клонируй репозиторий:

    ```bash
    git clone https://github.com/codeIsLive63/JEnumerable.git
    ```

2.  Перейди в каталог проекта:

    ```bash
    cd JEnumerable
    ```

3.  Собери проект (используя предпочитаемый инструмент сборки, например, Maven или Gradle). Поскольку предоставленный код не включает сценарии сборки, тебе нужно будет создать их самостоятельно на основе настроек проекта. Например, с Maven:

    ```bash
    mvn clean install
    ```

    (Тебе нужно будет создать файл pom.xml в корне проекта. Базовый файл pom.xml не включен.)

## Руководство по использованию

Несколько примеров того, как использовать JEnumerable:

**Пример 1: Преобразование многомерного списка в одномерный**

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

**Пример 2:  Выбор любимых языков**

```java
import codeislive63.collections.generic.List;
import codeislive63.jenumerable.JEnumerable;
import codeislive63.Person; // Предполагается, что класс Person определен

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

**Пример 3: Использование `count` и `take`**

```java
import codeislive63.collections.generic.List;
import codeislive63.jenumerable.JEnumerable;
import codeislive63.Person; // Предполагается, что класс Person определен

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

Эти примеры демонстрируют основные способы использования JEnumerable для работы с коллекциями. Смотри файл `src/codeislive63/Main.java` для более полных примеров.

## API Документация

### JEnumerable Class

*   **`static <TCollection> JEnumerable<TCollection> from(Enumerable<TCollection> collection)`**: Создает `JEnumerable` из объекта Enumerable.
*   **`static <TCollection> JEnumerable<TCollection> from(TCollection... collection)`**: Создает `JEnumerable` из переменного числа аргументов (массива).
*   **`static <TCollection> JEnumerable<TCollection> from(Iterable<TCollection> collection)`**: Создает `JEnumerable` из коллекции Iterable.
*   **`<TResult> JEnumerable<TResult> select(Func<TSource, TResult> selector)`**: Проецирует каждый элемент последовательности в новую форму с использованием функции селектора.
*   **`<TResult> JEnumerable<TResult> select(BiFunc<TSource, Integer, TResult> selector)`**: Проецирует каждый элемент последовательности в новую форму с учетом индекса элемента.
*   **`<TResult> JEnumerable<TResult> selectMany(Func<TSource, Enumerable<TResult>> selector)`**: Проецирует каждый элемент последовательности в `Enumerable<TResult>` и объединяет результат в одну последовательность.
*   **`<TResult> JEnumerable<TResult> selectMany(BiFunc<TSource, Integer, Enumerable<TResult>> selector)`**: Проецирует каждый элемент последовательности в `Enumerable<TResult>`, учитывая индекс элемента, и объединяет результат в одну последовательность.
*   **`<TCollection, TResult> JEnumerable<TResult> selectMany(Func<TSource, Enumerable<TCollection>> collectionSelector, BiFunc<TSource, TCollection, TResult> resultSelector)`**: Проецирует каждый элемент последовательности в `Enumerable<TCollection>` и вызывает функцию для обработки каждого элемента результирующей последовательности.
*   **`<TCollection, TResult> JEnumerable<TResult> selectMany(BiFunc<TSource, Integer, Enumerable<TCollection>> collectionSelector, BiFunc<TSource, TCollection, TResult> resultSelector)`**: Проецирует каждый элемент последовательности в `Enumerable<TCollection>`, учитывая индекс элемента, и вызывает функцию для обработки каждого элемента результирующей последовательности.
*   **`JEnumerable<TSource> where(Predicate<TSource> predicate)`**: Фильтрует последовательность значений на основе предиката.
*   **`JEnumerable<TSource> where(BiFunc<TSource, Integer, Boolean> predicate)`**: Фильтрует последовательность значений на основе предиката, где индекс элемента также используется в предикате.
*   **`int count()`**: Возвращает количество элементов в последовательности.
*   **`int count(Func<TSource, Boolean> predicate)`**: Возвращает количество элементов в последовательности, которые удовлетворяют условию заданного предиката.
*   **`JEnumerable<TSource> take(int count)`**: Возвращает указанное количество элементов с начала последовательности.
*   **`List<TSource> toList()`**: Преобразует последовательность `JEnumerable` в `List`.
*   **`TSource[] toArray()`**: Преобразует последовательность `JEnumerable` в массив.
*   **`Stream<TSource> asStream()`**: Преобразует последовательность `JEnumerable` в последовательный поток элементов.
*   **`Stream<TSource> asParallelStream()`**: Преобразует последовательность `JEnumerable` в параллельный поток элементов.
*   **`boolean any(Predicate<TSource> predicate)`**: Определяет, удовлетворяет ли хотя бы один элемент последовательности заданному условию.
*   **`boolean all(Predicate<TSource> predicate)`**: Определяет, удовлетворяют ли все элементы последовательности заданному условию.
*   **`Enumerator<TSource> getEnumerator()`**: Возвращает перечислитель, который итерирует по коллекции.

### Интерфейсы

*   **`Enumerable<T>`**: Представляет коллекцию, которая может быть перечислена.
*   **`Collection<T>`**: Расширяет `Enumerable` и предоставляет методы для добавления, удаления и очистки элементов.
*   **`ModifiableList<T>`**: Расширяет `Collection` и позволяет доступ к элементам по индексу, поддерживая операции модификации.
*   **`Enumerator<T>`**: Предоставляет методы для итерации по коллекции.

### Делегаты

Некоторые функциональные интерфейсы определены в пакетах `codeislive63.delegates` и `codeislive63.delegates.generic`, включая:

*   **`Action<T>`**: Представляет действие, которое принимает один аргумент.
*   **`AnyAction`**: Представляет действие, которое не принимает аргументов.
*   **`Func<T1, TResult>`**: Представляет функцию, которая принимает один аргумент и возвращает результат.
*   **`AnyFunc<TResult>`**: Представляет функцию, которая не принимает аргументов и возвращает результат.
*   **`BiFunc<T1, T2, TResult>`**: Представляет функцию, которая принимает два аргумента и возвращает результат.
*   **`Predicate<T>`**: Представляет функцию, которая принимает один аргумент и возвращает логическое значение.

## Контактная информация

Если у вас есть вопросы, вы можете сообщить об ошибках или предложить новые функции. Для этого создайте задачу в [репозитории GitHub](https://github.com/codeIsLive63/JEnumerable).
