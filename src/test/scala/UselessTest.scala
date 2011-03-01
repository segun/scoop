/**
 * Created by IntelliJ IDEA.
 * User: trinisoftinc
 * Date: 3/2/11
 * Time: 12:17 AM
 * To change this template use File | Settings | File Templates.
 */

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class UselessTest extends FlatSpec with ShouldMatchers {
  "A String " should " be equal to another String " in {
    "olu" should equal ("olu")
  }

  it should " also throw some kind of Exception if we tried to get wrong index " in {
    evaluating {
      "olu".charAt(5)
    } should produce [Exception]
  }
}