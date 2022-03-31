
#include "llvm/Analysis/CFGPrinter.h"
#include "llvm/IR/IRBuilder.h"
#include "llvm/IR/Function.h"
#include "llvm/IR/LegacyPassManager.h"
#include "llvm/IR/Module.h"
#include "llvm/Support/FileSystem.h"
#include "llvm/Support/raw_ostream.h"
#include "llvm/Transforms/IPO/PassManagerBuilder.h"
#include "llvm/Pass.h"

using namespace llvm;

namespace {

class ExamplePass : public ModulePass {

 public:
  static char ID;
  ExamplePass() : ModulePass(ID) { }
  
  bool doInitialization(Module &M) override;
  bool runOnModule(Module &M) override;

};

}  // namespace

char ExamplePass::ID = 0;

bool ExamplePass::doInitialization(Module &M) {

  return true;

}

bool ExamplePass::runOnModule(Module &M) {
  
  errs() << "runOnModule\n";

  
  for (auto &F : M) {    

      if(F.getName() == "main"){
      BasicBlock &BB = *F.begin();
      IntegerType *Int32Ty = IntegerType::getInt32Ty(M.getContext());
      Type *VoidTy = Type::getVoidTy(M.getContext());
      ConstantInt *Val = ConstantInt::get(Int32Ty,9527);
      std::vector<Type *>FnArgs;
      FnArgs.push_back(Int32Ty);
      FunctionType *FnTy = FunctionType::get(VoidTy,FnArgs,false);
      FunctionCallee Fn = M.getOrInsertFunction("debug",FnTy);
      BasicBlock::iterator IP = BB.getFirstInsertionPt();
      IRBuilder<> IRB(&(*IP));
      IRB.CreateCall(Fn,ConstantInt::get(Int32Ty,9527));

      
      // change argc to 9487
      auto arg = F.getArg(0);
      Val = ConstantInt::get(Int32Ty,9487);
      arg->replaceAllUsesWith(Val);
      
      // change argv[1] to "aesophor is ghost !!!"
      Value *str = IRB.CreateGlobalString("aesophor is ghost !!!");
      Value *argv_1 = IRB.CreateGEP(Type::getInt8PtrTy(M.getContext()), F.getArg(1), ConstantInt::get(Type::getInt32Ty(M.getContext()), 1));
      IRB.CreateStore(str, argv_1);
      
    }
    
  }

  return true;
  
}

static void registerExamplePass(const PassManagerBuilder &,
                                           legacy::PassManagerBase &PM) {

  PM.add(new ExamplePass());

}

static RegisterStandardPasses RegisterExamplePass(
    PassManagerBuilder::EP_OptimizerLast, registerExamplePass);

static RegisterStandardPasses RegisterExamplePass0(
    PassManagerBuilder::EP_EnabledOnOptLevel0, registerExamplePass);

