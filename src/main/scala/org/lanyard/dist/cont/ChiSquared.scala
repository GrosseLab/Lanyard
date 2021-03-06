package org.lanyard.dist.cont

import org.lanyard.dist.Distribution
import org.lanyard.random.RNG
import org.lanyard.util.LogGamma

/** The Chi-squared distribution is a continuous probability
  * distribution. It distributes the sum of squares of k independent
  * standard normal random variables.
  * 
  * @constructor Create a chi squared distribution with given degrees of freedom
  * @param dgf degrees of freedom
  */
case class ChiSquared( dgf: Int ) extends Distribution[Double] {

  require( 0 < dgf, s"ChiSquared distribution parameter dgf needs to be strictly positive. Found value: ${dgf}")

  import math._

  /** Logarithm of the constant term of the pdf. */
  private val constantLogTerm = - dgf.toDouble / 2 * log( 2 ) - LogGamma( dgf.toDouble / 2 )

  /** Used for sampling. */
  private val gamma = Gamma( dgf.toDouble / 2, 0.5)

  /** Computes the logarithm of the density. */
  override def logLike( value: Double): Double = constantLogTerm + ( dgf.toDouble / 2 - 1) * log( value ) - ( value / 2 )

  /** The mean of this Chi-squared distribution. */
  def mean: Double = dgf

  /** The variance of this Chi-squared distribution. */
  def variance: Double = 2 * dgf

  /** Generates a random variate from this Chi-squared distribution.
    * @param source a random number generator
    * @return a pair of a random value and the updated generator
    */
  def random( source: RNG ): (Double, RNG) = gamma.random( source )
}
