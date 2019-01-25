This library provides [JUnit QuickCheck](http://pholser.github.io/junit-quickcheck) generators for [Vavr](http://www.vavr.io/).

# Supported Vavr structures
* Option
* List
* Tuple0..8
* Map

# Quickstart
Gradle
```kotlin
testImplementation("com.sir4ur0n", "junit-quickcheck-vavr", "1.0")
```
Maven
```xml
<dependency>
	<groupId>com.sir4ur0n</groupId>
	<artifactId>junit-quickcheck-vavr</artifactId>
	<version>1.0</version>
	<scope>test</scope>
</dependency>
```

Now Vavr structures are supported inside all your JUnit QuickCheck tests:
```java
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.Tuple3;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class MyBusinessClassTest {
  
  private MyBusinessClass cut;
  
  @Before
  public void setUp() {
    cut = new MyBusinessClass();
  }
  
  @Property
  public void myBusinessTest(
      Option<String> vavrOption,
      List<String> vavrList,
      Tuple3<String, Boolean, Integer> vavrTuple,
      Map<Integer, String> vavrMap,
      PojoWithVavrFields pojoWithVavrFields) {
    // Your regular Property Based Test goes here
  }
}
```

# Develop
## Build

```
./gradlew build
```

## Test

```
./gradlew test
```

## Install on local .m2 repository

```
./gradlew publishToMavenLocal
```

## Publish to Bintray
You need to provide a `gradle.properties` file with Bintray credentials, e.g.
```
bintrayRepo=sir4ur0n-repository
bintrayUser=sir4ur0n
bintrayKey=1234567890
```
Then:
```
./gradlew bintrayUpload
```

## Intellij import 
* In `Preferences / Build, Execution, Deployment / Compiler / Annotations Processors`, enable `annotation processing`
* Import: `File / New.. / Project from existing sources / Gradle / Gradle wrapper`
