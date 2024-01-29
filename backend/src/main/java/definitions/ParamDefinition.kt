package definitions

import com.displee.io.impl.InputBuffer

class ParamDefinition(val id: Int = 0) {
    var stackType: Char = 0.toChar()
    var defaultInt: Int = 0
    var defaultString: String? = null
    var autoDisable: Boolean = true
    companion object {
        @JvmStatic
        fun ParamDefinition.decode(buffer: InputBuffer) {
            while (true) {
                val opcode: Int = buffer.readUnsignedByte()
                if (opcode == 0) {
                    return
                }
                decode(buffer, opcode)
            }
        }

        @JvmStatic
        private fun ParamDefinition.decode(buffer: InputBuffer, opcode: Int) {
            when (opcode) {
                1 -> {
                    stackType = InputBuffer.byteToChar(buffer.readUnsignedByte().toByte()) ?:
                    return
                }

                2 -> {
                    defaultInt = buffer.readInt()
                    return
                }

                4 -> {
                    autoDisable = false
                    return
                }

                5 -> defaultString = buffer.readString()
            }
        }
    }

    override fun toString(): String {
        return "ParamDefinitions(id=$id, stackType=$stackType, defaultInt=$defaultInt, defaultString=$defaultString," +
                " autoDisable=$autoDisable)"
    }
}