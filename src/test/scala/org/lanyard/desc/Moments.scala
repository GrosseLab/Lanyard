package org.lanyard.desc

case class Moments( moment0: Long = 0, moment1: Double = 0, moment2: Double = 0, moment3: Double = 0, moment4: Double = 0) {

  def :+ (value: Double): Moments = {
    val n1 = moment0 + 1
    val n2 = moment0 * moment0
    val delta = ( moment1 - value ) / n1
    val d2 = delta * delta
    val d3 = d2 * delta
    val r1 = moment0.toDouble / n1
    new Moments(
      n1,
      moment1 - delta,
      (moment2 + n1 * d2) * r1,
      (moment3 + (3 * delta * moment2 + (1 - n2) * d3)) * r1,
      (moment4 * (4 * delta * moment3 + 6 * d2 * moment2 + (1 + moment0 * n2) * d2 * d2)) * r1)
  }

  def count: Long = moment0

  def average: Double = moment1

  def variance: Double = (moment2 * moment0) / (moment0 - 1)

}
