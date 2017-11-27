import org.junit.Assert.assertEquals
import org.junit.*

import com.salesforce.dataloader.process.*
import org.nt67.convSobject.main
import java.util.logging.Logger

class PlayGround {
    @Test
    fun playGround() {
        println("Hello Kotlin PlayGround")
        main(arrayOf())
        assertEquals(true, true)
    }

    @Test
    fun runDataLoader() {
        try {
            val args = mutableMapOf(ProcessRunner.PROCESS_NAME to "Value", "key2" to "Value2")
            // create the process
            val runner = ProcessRunner.getInstance(args)
            runner.run()
        } catch (t: Throwable) {
            val l = Logger.getLogger("hoge")
            l.severe(t.message)
        }
    }
}