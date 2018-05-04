using BusinessEntities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BusinessServices
{
    public interface IRecipeServices
    {
        void Add(RecipeEntity recipe);
        void Update(RecipeEntity recipe);
        void Delete(int recipeID);
        RecipeEntity Get(int recipeID);
        List<RecipeEntity> GetList();
        int GetRecipeCount();
    }
}
