```text
$ scala-cli test .
…annoying warnings…
Compiling project (test, Scala 2.13.11, JVM)
[error] ./test/ThingTests.scala:13:12
[error] Symbol 'type shapeless.$colon$colon' is missing from the classpath.
[error] This symbol is required by 'method thing.Thing.thing'.
[error] Make sure that type :: is in your classpath and check for conflicting dependencies with `-Ylog-classpath`.
[error] A full rebuild may help if 'Thing.class' was compiled against an incompatible version of shapeless.
[error]     assert(Thing.thing.head + 2 == 4)
[error]            ^^^^^
Error compiling project (test, Scala 2.13.11, JVM)
Compilation failed
```

Edit `test/ThingTests.scala`, comment out the line right after the
`Error at compile-time:` comment, un-comment the one right after the
`Error at runtime:` comment:
```text
$ scala-cli test . # might need to be run twice to get rid of the spurious stale error from Bloop
…annoying warnings…
Exception in thread "main" java.lang.NoClassDefFoundError: shapeless/HList
	at java.lang.Class.getDeclaredConstructors0(Native Method)
	at java.lang.Class.privateGetDeclaredConstructors(Class.java:2671)
	at java.lang.Class.getConstructors(Class.java:1651)
	at scala.build.testrunner.DynamicTestRunner$.matchFingerprints(DynamicTestRunner.scala:23)
	at scala.build.testrunner.DynamicTestRunner$.$anonfun$main$6(DynamicTestRunner.scala:243)
	at scala.collection.Iterator$$anon$10.nextCur(Iterator.scala:594)
	at scala.collection.Iterator$$anon$10.hasNext(Iterator.scala:608)
	at scala.collection.Iterator$$anon$6.hasNext(Iterator.scala:477)
	at scala.collection.Iterator$$anon$9.hasNext(Iterator.scala:583)
	at scala.collection.mutable.Growable.addAll(Growable.scala:61)
	at scala.collection.mutable.Growable.addAll$(Growable.scala:57)
	at scala.collection.immutable.VectorBuilder.addAll(Vector.scala:1822)
	at scala.collection.immutable.Vector$.from(Vector.scala:1394)
	at scala.collection.IterableOnceOps.toVector(IterableOnce.scala:1317)
	at scala.collection.IterableOnceOps.toVector$(IterableOnce.scala:1317)
	at scala.collection.AbstractIterator.toVector(Iterator.scala:1300)
	at scala.build.testrunner.DynamicTestRunner$.main(DynamicTestRunner.scala:254)
	at scala.build.testrunner.DynamicTestRunner.main(DynamicTestRunner.scala)
Caused by: java.lang.ClassNotFoundException: shapeless.HList
	at java.net.URLClassLoader.findClass(URLClassLoader.java:387)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:418)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:352)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:351)
	... 18 more
```
