/**
 * Created by bhavinprajapati on 15-12-15.
 */
package com.garner

import java.util.Date

class ScheduledShipment(day: Date, slot: Int, id: Int) {
  var _day: Date = day
  var _slot: Int = slot
  var _id: Int = id
}