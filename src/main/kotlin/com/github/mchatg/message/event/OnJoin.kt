package com.github.mchatg.message.event

import com.github.mchatg.Context
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import java.util.*
import kotlin.collections.HashMap

class OnJoin(context: Context) : Context by context {


    private val map: HashMap<String, Calendar> = HashMap()
    fun onCall(player: String) {

        val now = Calendar.getInstance()
        if (map[player] == null) {
            map[player] = now
            telegramBot.send(SendMessage(config.bot.groupId.toString(), "$player 已上线"))
            return
        }

        val last: Calendar = map[player]!!.clone() as Calendar
        last.add(Calendar.MINUTE, config.message.onJoinInterval)
        val isTimeOut: Boolean = now > last
        if (isTimeOut) {
            map[player] = now
            telegramBot.send(SendMessage(config.bot.groupId.toString(), "$player 已上线"))
            return
        }

    }

}
