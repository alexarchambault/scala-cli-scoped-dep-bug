//> using dep "org.scalameta::munit:0.7.29"

package thing

class ThingTests extends munit.FunSuite {

  test("simple") {
    assert(2 + 2 == 4)
  }

  test("other") {
    // Error at compile-time:
    // assert(Thing.thing.head + 2 == 4)

    // Error at runtime:
    assert(ThingValue.value + 2 == 4)
  }

}
