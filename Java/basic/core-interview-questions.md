# Core Interview Questions

Table of Contents
-----------------

* [Q1. Is Data Passed by Reference or by Value in Java?](#q1-is-data-passed-by-reference-or-by-value-in-java)
* [Q2. What Is the Difference Between Import and Static Imports?](#q2-what-is-the-difference-between-import-and-static-imports)
* [Q3. Which Access Modifiers Are Available in Java and What Is Their Purpose?](#q3-which-access-modifiers-are-available-in-java-and-what-is-their-purpose)
* [Q4. What Is the Difference Between JDK, JRE, and JVM?](#q4-what-is-the-difference-between-jdk-jre-and-jvm)
* [Q5. What Are the Methods of the Object Class and What Do They Do?](#q5-what-are-the-methods-of-the-object-class-and-what-do-they-do)
* [Q6. What Is an Enum and How We Can Use It?](#q6-what-is-an-enum-and-how-we-can-use-it)
* [Q7. What Are Two Types of Casting in Java? Which Exception May Be Thrown While Casting? How Can We Avoid It?](#q7-what-are-two-types-of-casting-in-java-which-exception-may-be-thrown-while-casting-how-can-we-avoid-it)
* [Q8. Why Is String an Immutable Class?](#q8-why-is-string-an-immutable-class)
* [References](#references)


### Q1. Is Data Passed by Reference or by Value in Java?

首先理清楚两个概念：

- 值传递：传递该 `object` 的一份拷贝
- 引用传递：传递该 `object` 的引用



在 `Java` 中，

1. 对于原始数据类型：

- byte
- short
- int
- long
- float
- double
- char
- boolean

存放的是其确切的值

2. 对于非原始数据类型（对象，包装类等）存放的是其引用值（address）



**两者都存放于栈内存中**



举个例子：



**App.java**

```java
public class App {

    @Test
    public void testPrimitives() {

        int x = 1;
        int y = 2;

        assertEquals(1, x);
        assertEquals(2, y);

        modifyPrimitives(x, y);

        // Test passed
        assertEquals(1, x);
        assertEquals(2, y);

    }

    public void modifyPrimitives(int x, int y) {
        x = 5;
        y = 10;
    }

    @Test
    public void testNonPrimitives() {

        Person a = new Person(1);
        Person b = new Person(2);

        assertEquals(1, a.age);
        assertEquals(2, b.age);

        modifyNonPrimitives(a, b);

        // Test passed
        assertEquals(2, a.age);
        assertEquals(2, b.age);

    }

    public void modifyNonPrimitives(Person a, Person b) {

        a.age++;

        Person newPerson = new Person(23);
        newPerson.age++;

    }

    public class Person {

        int age;

        public Person(int age) {
            this.age = age;
        }

    }

}
```





### Q2. What Is the Difference Between Import and Static Imports?



```java
import java.util.ArrayList; //specific class
import java.util.*; //all classes in util package

import static java.util.Collections.EMPTY_LIST;
```



**区别：** 调用方法 / 变量的时候可以直接写，而不用以  `className.function()` 的形式调用



### Q3. Which Access Modifiers Are Available in Java and What Is Their Purpose?

- private：只对当前类暴露

- default：对当前 `package` 暴露

- protected：在 `default` 的基础上，若子类不在当前 `package` 下也可以访问

- public：对所有开放

  



### Q4. What Is the Difference Between JDK, JRE, and JVM?

  <div align="center"> <img src="jdk-jre-jvm.png" width="50%"/> </div><br>





### Q5. What Are the Methods of the Object Class and What Do They Do?

  <div align="center"> <img src="image-20201208163029796.png" width="30%"/> </div><br>

- getClass: Returns the runtime class of this object
- hashCode: The hashCode method does return distinct integers for distinct objects
- equals: Indicates whether some other object is "equal to" this one
- clone:
- toString: Returns a string representation of the object
- notify:
- notifyAll:
- wait:
- finalize:



**App.java**

```java
public class App {

    public static void main(String[] args) {

        String a = "aaa";
        System.out.println(a.getClass());  // class java.lang.String

        Integer b = 100;
        System.out.println(b.getClass());  // class java.lang.Integer

    }

}
```





### Q6. What Is an Enum and How We Can Use It?

**优点：**

- 方便定义常量（更加 readable，尤其是当你需要使用 `public final static` 修饰的变量）
- 提供编译时期检查



### Q7. What Are Two Types of Casting in Java? Which Exception May Be Thrown While Casting? How Can We Avoid It?

```java
Object o = "string";
String str = (String) o; // it's ok

Object o2 = new Object();
String str2 = (String) o2; // ClassCastException will be thrown

if (o2 instanceof String) { // returns false
    String str3 = (String) o2;
}
```







### Q8. Why Is String an Immutable Class?







## References

- [Java Interview Questions](https://www.baeldung.com/java-interview-questions)
- [Cannot find symbol assertEquals](https://stackoverflow.com/questions/20631621/cannot-find-symbol-assertequals)
- [A Guide to Java Enums](https://www.baeldung.com/a-guide-to-java-enums)
- [Why String is Immutable in Java?](https://www.baeldung.com/java-string-immutable)