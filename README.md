# logalert
Clone project:

```git clone https://github.com/mir00sz/logalert.git```

Cd to the repo folder and build it:

```cd ~/logalert```

```./gradlew clean build```

Copy a new jar to your working directory:

```cp build/libs/event-alert-0.1.0.jar ./```

Run it:
```java -jar event-alert-0.1.0.jar INPUT_FILE_PATH```

INPUT_FILE_PATH must be a path to input file that is going to be processed