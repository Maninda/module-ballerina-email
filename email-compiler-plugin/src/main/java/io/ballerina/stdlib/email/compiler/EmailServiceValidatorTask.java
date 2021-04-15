/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.ballerina.stdlib.email.compiler;

import io.ballerina.compiler.api.symbols.ServiceDeclarationSymbol;
import io.ballerina.compiler.api.symbols.Symbol;
import io.ballerina.compiler.api.symbols.TypeDescKind;
import io.ballerina.compiler.api.symbols.TypeReferenceTypeSymbol;
import io.ballerina.compiler.api.symbols.TypeSymbol;
import io.ballerina.compiler.api.symbols.UnionTypeSymbol;
import io.ballerina.compiler.syntax.tree.ImportDeclarationNode;
import io.ballerina.compiler.syntax.tree.ModulePartNode;
import io.ballerina.compiler.syntax.tree.ServiceDeclarationNode;
import io.ballerina.compiler.syntax.tree.SyntaxKind;
import io.ballerina.projects.plugins.AnalysisTask;
import io.ballerina.projects.plugins.SyntaxNodeAnalysisContext;

import java.util.List;
import java.util.Optional;

import static org.ballerinalang.stdlib.email.util.EmailConstants.MODULE_NAME;
import static org.ballerinalang.stdlib.email.util.EmailConstants.ORG_NAME;

/**
 * Validate Email Services.
 */
public class EmailServiceValidatorTask implements AnalysisTask<SyntaxNodeAnalysisContext> {

    public static final String CODE_101 = "EMAIL_101";
    public static final String CODE_102 = "EMAIL_102";
    public static final String CODE_103 = "EMAIL_103";
    public static final String CODE_104 = "EMAIL_104";
    public static final String CODE_105 = "EMAIL_105";
    public static final String CODE_106 = "EMAIL_106";
    public static final String CODE_107 = "EMAIL_107";
    public static final String SERVICE_MUST_CONTAIN_ON_MESSAGE_FUNCTION
            = "Service must contain `onMessage` function.";
    public static final String NO_PARAMETER_PROVIDED_FOR_0_FUNCTION_EXPECTS_1_AS_A_PARAMETER
            = "No parameter provided for `{0}`, function expects `{1}` as a parameter.";
    public static final String REMOTE_KEYWORD_EXPECTED_IN_0_FUNCTION_SIGNATURE
            = "`remote` keyword expected in `{0}` function signature.";
    public static final String RESOURCE_KEYWORD_NOT_EXPECTED_IN_0_FUNCTION_SIGNATURE
            = "`resource` keyword not expected in `{0}` function signature.";
    public static final String INVALID_PARAMETER_0_PROVIDED_FOR_1_FUNCTION_EXPECTS_2
            = "Invalid parameter `{0}` provided for `{1}`, function expects `{2}`.";
    public static final String INVALID_RETURN_TYPE_0_FUNCTION_1_RETURN_TYPE_SHOULD_BE_A_SUBTYPE_OF_2
            = "Invalid return type `{0}` provided for function `{1}`, return type should be a subtype of `{2}`";
    public static final String FUNCTION_0_NOT_ACCEPTED_BY_THE_SERVICE = "Function `{0}` not accepted by the service";
    public static final String NILL = "()";

    @Override
    public void perform(SyntaxNodeAnalysisContext ctx) {
        ServiceDeclarationNode serviceDeclarationNode = (ServiceDeclarationNode) ctx.node();
        String modulePrefix = getPrefix(ctx);

        Optional<Symbol> optionalServiceDeclarationSymbol = ctx.semanticModel().symbol(serviceDeclarationNode);
        EmailServiceValidator emailServiceValidator;
        if (optionalServiceDeclarationSymbol.isPresent() &&
                (optionalServiceDeclarationSymbol.get() instanceof ServiceDeclarationSymbol)) {

            ServiceDeclarationSymbol serviceDeclarationSymbol = (ServiceDeclarationSymbol)
                    optionalServiceDeclarationSymbol.get();

            if (isBallerinaEmailService(serviceDeclarationSymbol)) {
                emailServiceValidator = new EmailServiceValidator(ctx, modulePrefix
                        + SyntaxKind.COLON_TOKEN.stringValue());
                emailServiceValidator.validate();


//                String serviceName = serviceNameFromServiceDeclarationNode(serviceDeclarationNode);
//                serviceDeclarationNode.members().stream().filter(child ->
//                        child.kind() == SyntaxKind.OBJECT_METHOD_DEFINITION
//                                || child.kind() == SyntaxKind.RESOURCE_ACCESSOR_DEFINITION).forEach(node -> {
//
//                    FunctionDefinitionNode functionDefinitionNode = (FunctionDefinitionNode) node;
//                    // Check functions are remote or not
//                    validateServiceFunctions(functionDefinitionNode, syntaxNodeAnalysisContext);
//                    // Check params and return types
//                    validateFunctionSignature(functionDefinitionNode, syntaxNodeAnalysisContext, serviceName);
//
//                });
            }











//            List<TypeSymbol> listenerTypes = ((ServiceDeclarationSymbol) optionalServiceDeclarationSymbol.get())
//                    .listenerTypes();
//            ((BUnionType) (((TypeReferenceTypeSymbol) optionalServiceDeclarationSymbol).value).bType).memberTypes
//              .stream().allMatch(bType -> bType.tsymbol.name.equals("PopListener")
//                  || bType.tsymbol.name.equals("Error"))
//            for (TypeSymbol listenerType : listenerTypes) {
//                if (isListenerBelongsToEmailModule(listenerType)) {
//                    emailServiceValidator = new EmailServiceValidator(ctx, modulePrefix
//                            + SyntaxKind.COLON_TOKEN.stringValue());
//                    emailServiceValidator.validate();
//                    return;
//                }
//            }
        }

//        ServiceDeclarationNode serviceDeclarationNode = (ServiceDeclarationNode) ctx.node();
//        SeparatedNodeList<ExpressionNode> expressions = serviceDeclarationNode.expressions();
//
//        String modulePrefix = MODULE_NAME;
//        ModulePartNode modulePartNode = ctx.syntaxTree().rootNode();
//        for (ImportDeclarationNode importDeclaration : modulePartNode.imports()) {
//            if (importDeclaration.moduleName().get(0).toString().stripTrailing()
//                    .compareTo(MODULE_NAME) == 0) {
//                if (importDeclaration.prefix().isPresent()) {
//                    modulePrefix = importDeclaration.prefix().get().children().get(1).toString();
//                }
//                break;
//            }
//        }
//
//        for (ExpressionNode expressionNode : expressions) {
//            if (expressionNode.kind() == SyntaxKind.EXPLICIT_NEW_EXPRESSION) {
//                TypeDescriptorNode typeDescriptorNode = ((ExplicitNewExpressionNode) expressionNode).typeDescriptor();
//                Node moduleIdentifierTokenOfListener = typeDescriptorNode.children().get(0);
//                if (moduleIdentifierTokenOfListener.toString().compareTo(modulePrefix) == 0) {
//                    this.ctx = ctx;
//                    this.modulePrefix = modulePrefix + SyntaxKind.COLON_TOKEN.stringValue();
//                    this.validate();
//                }
//            }
//        }

    }

//    private String serviceNameFromServiceDeclarationNode(ServiceDeclarationNode serviceDeclarationNode) {
//
//        NodeList<Node> nodeList = serviceDeclarationNode.absoluteResourcePath();
//        if (nodeList.size() > 0) {
//            String serviceName = serviceDeclarationNode.absoluteResourcePath().get(0).toString();
//            return serviceName.replaceAll("\"", "").strip();
//        }
//        return "";
//    }

    private boolean isBallerinaEmailService(ServiceDeclarationSymbol serviceDeclarationSymbol) {

        List<TypeSymbol> listenerTypes = serviceDeclarationSymbol.listenerTypes();
        for (TypeSymbol listenerType : listenerTypes) {
            if (listenerType.typeKind() == TypeDescKind.UNION) {
                List<TypeSymbol> memberDescriptors = ((UnionTypeSymbol) listenerType).memberTypeDescriptors();
                for (TypeSymbol typeSymbol : memberDescriptors) {
                    if (typeSymbol.getModule().isPresent() && typeSymbol.getModule().get().id().orgName()
                            .equals(ORG_NAME) && typeSymbol.getModule()
                            .flatMap(Symbol::getName).orElse("").equals(MODULE_NAME)) {

                        return true;
                    }
                }
            } else if (listenerType.typeKind() == TypeDescKind.TYPE_REFERENCE
                    && listenerType.getModule().isPresent()
                    && listenerType.getModule().get().id().orgName().equals(ORG_NAME)
                    && ((TypeReferenceTypeSymbol) listenerType).typeDescriptor().getModule()
                    .flatMap(Symbol::getName).orElse("").equals(MODULE_NAME)) {

                return true;
            }
        }
        return false;
    }

    private String getPrefix(SyntaxNodeAnalysisContext ctx) {
        ModulePartNode modulePartNode = ctx.syntaxTree().rootNode();
        for (ImportDeclarationNode importDeclaration : modulePartNode.imports()) {
            if (importDeclaration.moduleName().get(0).toString().stripTrailing().compareTo(MODULE_NAME) == 0) {
                if (importDeclaration.prefix().isPresent()) {
                    return importDeclaration.prefix().get().children().get(1).toString();
                }
                break;
            }
        }
        return MODULE_NAME;
    }

//    private boolean isListenerBelongsToEmailModule(TypeSymbol listenerType) {
//        if (listenerType.typeKind() == TypeDescKind.UNION) {
//            return ((UnionTypeSymbol) listenerType).memberTypeDescriptors().stream()
//                    .filter(typeDescriptor -> typeDescriptor instanceof TypeReferenceTypeSymbol)
//                    .map(typeReferenceTypeSymbol -> (TypeReferenceTypeSymbol) typeReferenceTypeSymbol)
//                    .anyMatch(typeReferenceTypeSymbol -> isEmailModule(typeReferenceTypeSymbol.getModule().get()));
//        }
//
//        if (listenerType.typeKind() == TypeDescKind.TYPE_REFERENCE) {
//            return isEmailModule(((TypeReferenceTypeSymbol) listenerType).typeDescriptor().getModule().get());
//        }
//
//        return false;
//    }

//    private boolean isEmailModule(ModuleSymbol moduleSymbol) {
//        if (moduleSymbol.getName().isPresent()) {
//            return (moduleSymbol.getName().get().compareTo(MODULE_NAME) == 0)
//                    && (moduleSymbol.id().orgName().compareTo(ORG_NAME) == 0);
//        } else {
//            return false;
//        }
//    }

}
