package codeislive63;

import codeislive63.collections.generic.List;

//Класс для экспериментов :)
class Person {

    private final String _name;
    private final int _age;
    private final List<String> _favoriteLanguages;

    Person(String name, int age, String... favoriteLanguages) {
        _name = name;
        _age = age;
        _favoriteLanguages = new List<>(favoriteLanguages);
    }

    public String getName() {
        return _name;
    }

    public int getAge() {
        return _age;
    }

    public List<String> getFavoriteLanguages() {
        return _favoriteLanguages;
    }
}