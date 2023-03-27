package markdown

import org.intellij.markdown.ast.ASTNode

interface TagConsumer<in Tag, out Parent> {
    fun consumeTagOpen(node: ASTNode, tag: Tag, autoClose: Boolean = false)

    fun consumeTagClose(tag: Tag)

    fun consume(invoker: Parent.() -> Unit)
}
