//> using target.scala "2"
//> using dep "com.chuusai::shapeless:2.3.10"

package thing

import shapeless._

object Thing {
  def thing: Int :: String :: HNil = 2 :: "a" :: HNil
}
