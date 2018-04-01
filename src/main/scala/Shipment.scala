/**
 * Created by bhavinprajapati on 15-12-15.
 */
package com.garner

class Shipment(id: Int, description: String, weight: Double, unit: String, priority: Int) {
  var _id: Int = id
  var _description: String = description
  var _weight: Double = weight
  var _unit: String = unit
  var _priority: Int = priority
}