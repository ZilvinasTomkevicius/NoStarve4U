using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BusinessEntities;

namespace BusinessServices
{
    public interface IRecipeProductServices
    {
        RecipeProductsEntity Get(int recipeID);
        void Add(RecipeProductsEntity recipeProductsEntity);
    }
}
