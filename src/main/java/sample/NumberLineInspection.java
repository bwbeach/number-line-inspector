package sample;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NumberLineInspection extends LocalInspectionTool {

    @Override
    public @Nullable String getStaticDescription() {
        return super.getStaticDescription();
    }

    @Override
    public @NotNull PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        return new JavaElementVisitor() {
            @Override
            public void visitBinaryExpression(PsiBinaryExpression expression) {
                super.visitBinaryExpression(expression);
                if (expression.getOperationTokenType() == JavaTokenType.GT) {
                    holder.registerProblem(
                            expression,
                            "Not in number line order",
                            new SwapOperationsQuickFix()
                    );
                }
            }
        };
    }

    private static class SwapOperationsQuickFix implements LocalQuickFix {

        @Override
        public @Nls(capitalization = Nls.Capitalization.Sentence) @NotNull String getName() {
            return "Reverse inequality";
        }

        @Override
        public @Nls(capitalization = Nls.Capitalization.Sentence) @NotNull String getFamilyName() {
            return getName();
        }

        @Override
        public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
            PsiBinaryExpression binaryExpression = (PsiBinaryExpression) descriptor.getPsiElement();
            PsiExpression left = binaryExpression.getLOperand();
            PsiExpression right = binaryExpression.getROperand();
            if (right == null) {
                return;
            }

            PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();
            PsiBinaryExpression updatedExpression =
                    (PsiBinaryExpression) factory.createExpressionFromText("a < b", null);
            updatedExpression.getLOperand().replace(right);
            PsiExpression updatedRight = updatedExpression.getROperand();
            if (updatedRight != null) {  // will never be null, but this avoids a warning
                updatedRight.replace(left);
            }

            binaryExpression.replace(updatedExpression);
        }
    }
}
